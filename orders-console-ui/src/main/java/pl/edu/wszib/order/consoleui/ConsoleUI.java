package pl.edu.wszib.order.consoleui;

import pl.edu.wszib.order.api.OrderApi;
import pl.edu.wszib.order.api.OrderApiResult;
import pl.edu.wszib.order.api.OrderItemApi;
import pl.edu.wszib.order.api.OrderState;
import pl.edu.wszib.order.application.order.Order;
import pl.edu.wszib.order.application.product.Product;
import pl.edu.wszib.order.application.product.ProductId;
import pl.edu.wszib.order.application.product.ProductModule;

import java.math.BigDecimal;
import java.util.Scanner;
import java.util.Set;
import java.util.UUID;

public class ConsoleUI {
    private final Scanner scanner = new Scanner(System.in);
    OrderApi order;
    public void run() {
        while(true) {
            showMenu();
            String option = waitForInput();
            switch (option) {
                case "1":
                    order = createOrder();
                    System.out.println(order.getId());
                    break;
                case "2":
                    System.out.println("Podaj id zamowienia: ");
                    String givenId = scanner.nextLine();
                    findById(givenId);
                    break;
                case "3":
                    System.out.println("Podaj id zamowienia: ");
                    String givenId2 = scanner.nextLine();
                    addProductToOrder(givenId2);
                case "0":
                    return;
            }
        }
    }

    private String waitForInput() {
        System.out.println("Wybieram opcję: ");
        return scanner.nextLine();

    }

    private void showMenu() {
        System.out.println("1. Utwórz zamówienie" +
                "\n2. Wyszukaj zamówienie" +
                "\n3. Dodaj produkt do zamowienia" +
                "\n0. Wyjdz");
    }

    private OrderApi createOrder() {

       return new OrderApi(UUID.randomUUID().toString(),
               OrderState.CREATED,
               Set.of(new OrderItemApi(UUID.randomUUID().toString(), "Test product", BigDecimal.valueOf(4), 1, BigDecimal.valueOf(4))),
               BigDecimal.valueOf(4));
    }

    private void findById(final String id) {
        if(id.equals(order.getId())){
            System.out.println("Id: " + order.getId());
            System.out.println("Ilosc: " + order.getAmount());
            System.out.println("Produkty: " + order.getItems());
            System.out.println("Stan: " + order.getState());
        }else {
            System.out.println("Nie ma zamowienia o takim Id");
        }
    }

    private void addProductToOrder(final String id) {
        /*
        Product CHOCOLATE = new Product(ProductId.create(), "Czekolada", BigDecimal.valueOf(4));
        Product COCA_COLA_ZERO = new Product(ProductId.create(), "Coca-cola Zero", BigDecimal.valueOf(5));}
        */

    }

    // 1. Utwórz zamówienie
    // 2. Wyszukaj zamówienie
    // 3. Dodaj produkt do zamówienia
    // 4. Usuń produkt za zamówienia
    // 0. Wyjście

    // 1:
    // Wyświetl id utworzonego zamówienia

    // 2:
    // Wprowadź id zamówienia: ${id}
    // Wyświetl zamówienie na ekranie

    // 3:
    // Wprowadź id zamówienia: ${id}
    // Wyświetl listę dostępnych produktów

    // 4:
    // Wprowadź id zamówienia: ${id}
    // Wyświetl aktualną listę produktów
    // Wybierz produkt do usunięcia

    // 0. Wyjście
    // Zakońćzenie działania aplikacji
}
