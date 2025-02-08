package com.unir.operador.service;

import com.unir.operador.dto.ItemResponseDTO;
import com.unir.operador.dto.PurchaseRequest;
import com.unir.operador.dto.PurchaseItemDTO;
import com.unir.operador.facade.BooksFacade;
import com.unir.operador.model.Purchase;
import com.unir.operador.model.PurchaseDetail;
import com.unir.operador.repository.PurchaseJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

// LOGS
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseJpaRepository purchaseRepository;
    private BooksFacade booksFacade;

    public PurchaseServiceImpl(PurchaseJpaRepository purchaseRepository, BooksFacade booksFacade) {
        this.purchaseRepository = purchaseRepository;
        this.booksFacade = booksFacade;
    }

    /**
     * Crea la compra y persiste los detalles (One-to-Many).
     */
    @Override
    public Purchase crearCompraConDetalles(PurchaseRequest request) {
        // 1) Crear la cabecera Purchase
        Purchase purchase = new Purchase();
        purchase.setUserId(request.getUserId());
        purchase.setPurchaseDate(LocalDateTime.now());

        double totalCompra = 0.0;

        // 2) Iterar los ítems y generar los detalles
        List<PurchaseItemDTO> itemList = request.getItems();
        if (itemList != null && !itemList.isEmpty()) {
            for (PurchaseItemDTO itemDTO : itemList) {
                // Llamada al microservicio ms_buscador para obtener datos reales
                ItemResponseDTO itemInfo = booksFacade.getBook(itemDTO.getItemId().toString());
                if (itemInfo == null) {
                    // El ítem no existe o hubo error en la llamada
                    throw new RuntimeException("Item " + itemDTO.getItemId() + " no encontrado en ms_buscador");
                }

                // Verificar si está oculto
                if (!itemInfo.isVisible()) {
                    throw new RuntimeException("El ítem " + itemDTO.getItemId() + " está oculto y no se puede comprar");
                }

                // Verificar stock suficiente
                if (itemInfo.getStock() < itemDTO.getQuantity()) {
                    throw new RuntimeException("Stock insuficiente para item " + itemDTO.getItemId());
                }

                // Tomar el precio 'oficial' del microservicio, ignorando el que mande el
                // cliente
                double officialPrice = itemInfo.getPrice();

                // Calcular el lineTotal
                double lineTotal = officialPrice * itemDTO.getQuantity();

                // Crear PurchaseDetail
                PurchaseDetail detail = new PurchaseDetail();
                detail.setItemId(itemDTO.getItemId());
                detail.setQuantity(itemDTO.getQuantity());
                detail.setPricePerUnit(officialPrice);
                detail.setLineTotal(lineTotal);

                // Vincularlo con la cabecera
                purchase.addDetail(detail);

                // Acumular el total de la compra
                totalCompra += lineTotal;
            }
        }

        purchase.setTotal(totalCompra);

        // 3) Guardar (cascade ALL => guarda también los detalles)
        return purchaseRepository.save(purchase);
    }

    @Override
    public List<Purchase> obtenerTodas() {
        return purchaseRepository.findAll();
    }
}
