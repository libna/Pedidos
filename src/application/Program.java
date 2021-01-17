package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import entities.Client;
import entities.Order;
import entities.OrderItem;
import entities.Product;
import entities.enums.OrderStatus;

public class Program {

	public static void main(String[] args) throws ParseException {
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat formatdt = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat format_dt_hr = new SimpleDateFormat("DD/MM/YYYY HH:MM:ss");
		
		
		System.out.println("Enter cliente data");
		System.out.println("Name: ");
		String name = sc.nextLine();
		
		System.out.println("Email: ");
		String email = sc.nextLine();
		
		System.out.println("Birth date (DD/MM/YYYY): ");
		String data = sc.nextLine();
		
		System.out.println("Enter order Data: ");
		System.out.println("status: ");
		String status = sc.nextLine();
		
		Order ord = new Order(new Date(), OrderStatus.valueOf(status), new Client(name, email, formatdt.parse(data)));
		
		System.out.println("How many items to this order?");
		int quant_order = sc.nextInt();
		
		for (int i = 1; i <= quant_order; i++) {
			sc.nextLine();
			System.out.println("Enter #" + i + " item data: ");
			System.out.println("Product name: ");
			String produto = sc.nextLine();
			
			System.out.println("Product price: ");
			Double preco = sc.nextDouble();
			
			System.out.println("Product quantity: ");
			Integer quant = sc.nextInt();
			
			OrderItem oi = new OrderItem(quant, preco,new Product(produto,preco));
			
			ord.addItem(oi);
		}
		
		System.out.println("ORDER SUMARY");
		System.out.println("Order Moment: "+ format_dt_hr.format(ord.getMoment()));
		System.out.println(
				"Cliente: "+
				ord.getCliente().getName()+ " "+  
				formatdt.format(ord.getCliente().getBirthDate())+ " "+  
				ord.getCliente().getEmail());
		System.out.println("Order items: ");
		
		int sum = 0;
		for (OrderItem item : ord.getItems()) {
			System.out.println(
					item.getProd().getName()+ ", " +
					item.getProd().getPrice()+
					", Quantity: "+item.getQuantity()+","
					+ " Subtotal: "+item.subTotal() );
			sum += item.subTotal();
		}
		System.out.println("Total price: "+ sum);
		
		sc.close();

	}

}
