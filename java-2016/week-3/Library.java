import java.util.Scanner;
public class Library {
	
	private static final int NO_INDEX = -1;
	private Book [] books = new Book[100];
    private int indexBook = 0;
         

    public void makeDecision(){
    	boolean goOn = true;
    	do{
	        System.out.println("What do you want to do? (add book/delete book/list books/exit) ");
	        Scanner keyboard = new Scanner(System.in);
	        String input = keyboard.nextLine();
			
			if (input.equals("add book")) {
	            typeOfBook();
	        }
	
	        if (input.equals("delete book")){
	            System.out.println("Enter the title of the book you want to delete: ");
	            input = keyboard.nextLine();
	            int indexFound = findBook(input);
	            if (indexFound==NO_INDEX) {
					System.out.println("This book is not in the library.");
				}
				else {
					deleteBook(indexFound);
				}       
	        }
	    
	        
	        if (input.equals("list books")){
	        	System.out.println("What type of books do you want to see? (novels/art albums/all books)");
	        	input = keyboard.nextLine();
	        	if (input.equals("novels")){
	        		listNovels();
				}
	        	if (input.equals("art albums")){
	        		listArtAlbums();
	        	}
	        	if (input.equals("all books")){
	                listBooks(); 
	        	}
	        }
	        
			if (input.equals("exit")){
	        	goOn=false;
	        }
    	} while (goOn==true);
    }


    public int findBook(String input){
        for (int i=0; i<indexBook; i++){
            if (books[i].getName().equals(input)){
                return i;
            }
        }
        return NO_INDEX;
    }

    public void deleteBook(int indexFound){
        indexBook = indexBook-1;
        for (int i=indexFound; i<indexBook; i++){
            books[i] = books[i+1];
        }
        books[indexBook]=null;
    }

   
    public void addBook(Book b){
		
		if (indexBook < books.length){
			for (int i=0; i<indexBook; i++){
				if (books[i].equals(b)){
					System.out.println("The book can already be found in the library.");
					return;	
				}
			}
				books[indexBook] = b;
				indexBook++;
				
			
		}
		else {
			System.out.println("The library is full.");
		}
		
    }
    
    public void typeOfBook() {

        Scanner keyboard = new Scanner(System.in);
        System.out.print("Is it a novel or an art album? ");
            String input = keyboard.nextLine();
            if (input.contains("novel")){
               Book b = readNovel();
               addBook(b);
            }
            else if (input.contains("art album")){
                     Book b = readArtAlbum();
                     addBook(b);
                }
            else {
                System.out.println("It has to be a novel or an art album");

            }
    }

    public Book readNovel(){
        Scanner reader = new Scanner(System.in);

        System.out.print("Enter the name of the novel: ");
        String nameOfNovel = reader.nextLine();
        System.out.print("Enter the number of pages: ");
        String strNrPages = reader.nextLine();
        int nrOfPages = Integer.parseInt(strNrPages);

        System.out.print("Enter the type of the novel: ");
        String typeOfNovel = reader.nextLine();

        Book b = new Novel(nameOfNovel, nrOfPages, typeOfNovel);
        return b;
    }
    
    public Book readArtAlbum(){
        Scanner reader = new Scanner(System.in);

        System.out.print("Enter the name of the art album: ");
        String nameOfAlbum = reader.nextLine();
        System.out.print("Enter the number of pages: ");
        String strNrPages = reader.nextLine();
        int nrOfPages = Integer.parseInt(strNrPages);

        System.out.print("Enter the paper quality of the art album: ");
        String qualityOfPaper = reader.nextLine();

        Book b = new ArtAlbum(nameOfAlbum, nrOfPages, qualityOfPaper);
        return b;
    }
    
    
    //Method listBooks prints books in the order that books are kept in the array
    public void listBooks(){
        System.out.println("All books in the library ");
        for (int i=0; i<indexBook; i++){
        	if (books[i] instanceof Novel){
        		printNovel(books[i]);
        		
            }
        	if (books[i] instanceof ArtAlbum){
        		printArtAlbum(books[i]);
        	}
        }
    }
    
    public void printNovel(Book b){
    	Novel n = (Novel) b;
    	System.out.println(n.getName()+" "+n.getNrPages()+" pages "+ n.getType());
    }
    
    public void printArtAlbum(Book b){
    	ArtAlbum a = (ArtAlbum) b;
    	System.out.println(a.getName()+" "+ a.getNrPages()+" pages "+ a.getPaperQuality());
	}
            
    public void listNovels(){
        System.out.println("The library contains the following novels: ");
        for (int i=0; i<indexBook; i++){
            if (books[i] instanceof Novel){
            	printNovel(books[i]);
            }
        }
    }

    public void listArtAlbums(){
        System.out.println("The library contains the following art albums: ");
        for (int i=0; i<indexBook; i++){
            if (books[i] instanceof ArtAlbum){
            	printArtAlbum(books[i]);
            }
        }
    }

    public void populateArray(){
        Book novel1 = new Novel("Walden", 235, "fiction");
        addBook(novel1);

        Book novel2 = new Novel("Harry Potter: Prisoner of Azkaban", 556, "fantasy");
        addBook(novel2);

        Book album1 = new ArtAlbum("Monet", 147, "high-quality");
        addBook(album1);
        
        Book album2 = new ArtAlbum("History of photography", 490, "high-quality");
        addBook(album2);
        
        Book novel3 = new Novel("1984",242, "fiction");
        addBook(novel3);

        Book novel4 = new Novel("The Shining", 150, "horror");
        addBook(novel4);

        Book album3 = new ArtAlbum("The works of Jackson Pollock", 67, "medium-quality");
        addBook(album3);
    }

    public static void main (String [] args){

        Library libr = new Library();
        libr.populateArray();
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
		this.nrPages = newNrPages;
	}
	public int getNrPages(){
		return nrPages;
	}
	
	@Override
	 public boolean equals(Object o) {
        if (this == o) {
          return true;
        }
		
        if (!(o instanceof Book)) {
          return false;
        }
		
		Book book = (Book) o;
		
		if (name != null ? !name.equals(book.name) : book.name != null) {
          return false;
        }
		
		if (nrPages != book.nrPages) {
          return false;
        }
		
		return true;
    }
}

 class Novel extends Book {
	private String type;
	
	public Novel (String name, int nrPages, String type){
		super(name, nrPages);
		this.type = type;
	} 
	
	public void setType(String newType){
		this.type = newType;
	}
	
	public String getType(){
		return type;
	}
	
	@Override
	 public boolean equals(Object o) {
        if (this == o) {
          return true;
        }
		
        if (!(o instanceof Novel)) {
          return false;
        }
		
		Novel novel = (Novel) o;
		
		if (type != null ? !type.equals(novel.type) : novel.type != null) {
          return false;
        }
		
		return true;
    }
}

class ArtAlbum extends Book {
	private String paperQuality;
	
	public ArtAlbum(String name, int nrPages, String paperQuality){
		super(name, nrPages);
		this.paperQuality=paperQuality;
	}
        
    public void setPaperQuality(String newPaperQuality){
        this.paperQuality = newPaperQuality;
    }
        
    public String getPaperQuality(){
        return paperQuality;
    }
	@Override
	 public boolean equals(Object o) {
        if (this == o) {
          return true;
        }
		
        if (!(o instanceof ArtAlbum)) {
          return false;
        }
		
		ArtAlbum artAlbum = (ArtAlbum) o;
		
		if (paperQuality != null ? !paperQuality.equals(artAlbum.paperQuality) : artAlbum.paperQuality != null) {
          return false;
        }
		
		return true;
    }
	

}