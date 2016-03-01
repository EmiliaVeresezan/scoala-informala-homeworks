import java.util.Scanner;

public class Library {
	
	//private Novel [] novelArray;
	//private ArtAlbum [] artAlbumArray;
        private Book [] books = new Book[200];
        private int nextIndex=0;
        
//public Library (int i, int j){
//    novelArray = new Novel [i];
//    artAlbumArray = new ArtAlbum[j];
//}

public void makeDecision(){
    System.out.println("What do you want to do? (add book/delete book/list books ");
    Scanner keyboard = new Scanner(System.in);
    String input = keyboard.nextLine();
    if (input.equals("add book")) {
        Book b = readBook();
        addBook(b);
    }
    if (input.equals("delete book")){
    
    }
    if (input.equals("list books")){
        listBooks();
    }
    
}

    
public void addBook(Book b){
    if (nextIndex <books.length){
        books[nextIndex]=b;
        System.out.println("Book added at postion "+ nextIndex);
        nextIndex++;
    }

}

public Book readBook() {
    Book b ;
    Scanner keyboard = new Scanner(System.in);
    System.out.print("Is it a novel or an art album?");
        String input = keyboard.nextLine();
        System.out.println(input);
        if (input.contains("novel")){
           b = readNovel();
        }
        else if (input.contains("art album")){
                  b = readArtAlbum();
            }
        else {
            System.out.println("It has to be a novel ora an art album");
            b = null;
            
        }
        return b;
}

public Book readNovel(){
    Scanner reader = new Scanner(System.in);
    
    System.out.print("Enter the name of the novel ");
    String nameOfNovel = reader.nextLine();
    System.out.print("Enter the number of pages ");
    String strNrPages = reader.nextLine();
    int nrOfPages = Integer.parseInt(strNrPages);
    
    System.out.print("Enter the type of the novel ");
    String typeOfNovel = reader.nextLine();
    
    Book n = new Novel(nameOfNovel, nrOfPages, typeOfNovel);
    return n;
}
public Book readArtAlbum(){
    Scanner reader = new Scanner(System.in);
    
    System.out.print("Enter the name of the art album ");
    String nameOfAlbum = reader.nextLine();
    System.out.print("Enter the number of pages ");
    String strNrPages = reader.nextLine();
    int nrOfPages = Integer.parseInt(strNrPages);
    
    System.out.print("Enter the paper quality of the art album ");
    String qualityOfPaper = reader.nextLine();
    
    Book a = new ArtAlbum(nameOfAlbum, nrOfPages, qualityOfPaper);
    return a;
}
        
public void listBooks(){
    for (Book book: books){
        System.out.println(book.getName()+" "+book.getNrPages()+" " );
    }

}

public static void main (String [] args){
	
    Library libr = new Library();
    libr.makeDecision();
    
    
	
    }
}


class Book {
	private String name; 
	private int nrPages;
	
	public Book (String name, int nrPages){
		this.name = name;
		this.nrPages = nrPages;
	}
	
	public void setName(String newName){
		name = newName;
	}
	
	public String getName(){
		return name;
	}
	
	public void setNrPages(int newNrPages){
		nrPages = newNrPages;
	}
	public int getNrPages(){
		return nrPages;
	}
	
}


 class Novel extends Book {
	String type;
	//byte idNovel;
	
	
	public Novel (String name, int nrPages, String type){
		super(name, nrPages);
		this.type = type;
		//this.idNovel = idNovel;
		
	} 
	
	public void setType(String newType){
		type = newType;
	}
	
	public String getType(){
		return type;
	}
	

}
class ArtAlbum extends Book {
	String paperQuality;
	
	public ArtAlbum(String name, int nrPages, String paperQuality){
		super(name, nrPages);
		this.paperQuality=paperQuality;
		
		
		
	}
	

}