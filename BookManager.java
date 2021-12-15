package Library;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;


public class BookManager {
    ArrayList<Book> List = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    public BookManager() {
        loadFromFile();
    }

    public ArrayList<Book> getBooks() {
        return List;
    }

    
    
    public void printBooks(ArrayList<Book> books) {
        if (books.isEmpty()) {//Check List Book
            System.out.println("(Empty)");
            return;
        }
        System.out.printf("%-5s %-45s %-10s %n", "ID", "Name", "Price");
        books.forEach(System.out::println);
    }
    
    public boolean addBook(Book book_input) {
        for (Book book : List) {
            if (book.id == book_input.id) {
                return false;
            }
        }
        List.add(book_input);
        return true;
    }

    public Book getBookById(int id) {
        for (Book book : List) {
            if (book.id == id) return book;
        }
        System.out.println("Invalid ID");
        return null;
    }

    public void removeBook(Book book) {
        List.remove(book);
    }

    
    
    public void loadFromFile() {
        System.out.println("Loading books...");

        try {
            sc = new Scanner(new File("books.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("\"books.txt\" is not already");
            return;
        }
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.isEmpty()) continue;

            int id = Integer.parseInt(line.substring(0, 5).trim());
            String name = line.substring(6, 51).trim();
            double price = Double.parseDouble(line.substring(51).trim());

            List.add(new Book(id, name, price));
        }
    }


    public void saveToFile() {
        PrintWriter printWriter;
        try {
            printWriter = new PrintWriter("books.txt");
            for (Book book : List) {
                printWriter.println(book);
            }
            printWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("File saved!");
    }

    public void sortDescByPrice() {
        // Insertion sort
    	Collections.sort(List,new Comparator<Book>() 
		{
			@Override
			public int compare(Book o1, Book o2) 
			{
				// TODO Auto-generated method stub
				if(o1.getPrice()<o2.getPrice())
				{
					return -1;
				}
				return 1;
			}	
		});
    }

    public ArrayList<Book> searchByName(String keyword) {
        ArrayList<Book> result = new ArrayList<>();
        for (Book book : List) {
            if (book.name.toLowerCase().contains(keyword.toLowerCase())) {
                result.add(book);
            }
        }
        return result;
    }
}