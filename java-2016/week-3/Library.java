import java.util.Scanner;
public class Library {
	
	private static final int NO_INDEX = -1;
	private Book [] books = new Book[100];
    private int indexBook = 0;
	private Book [] booksFound = new Book[100];
         

    public void makeDecision(){
    	boolean goOn = true;
    	do{
	        System.out.println("What do you want to do? (add book/delete book/list books/exit) ");
	        Scanner keyboard = new Scanner(System.in);
	        String input = keyboard.nextLine();
			
			if (input.equals("add book")) {
                /* TODO - CODE REVIEW - You should use a more descriptive name, that hints at the desired action 
                like: iquireBookType() or askBookType()
                */
	            typeOfBook();
	        }
	
	        if (input.equals("delete book")){
	            System.out.println("Enter the title of the book you want to delete: ");
	            input = keyboard.nextLine();
	            
				int size = findBook(input);
				
				if (size == 0){
					System.out.println("No books with the title "+ input +" have been found.");
				}
				if (size==1){
					deleteBook(booksFound[0]);
				}
				else {
					System.out.println("The following books with the title " + input + " have been found: ");
					for (int i=0; i<size;i++){
						System.out.print((i+1)+". ");
						printBook(booksFound[i]);
					}
					System.out.println("Which book do you want to delete? Please enter a number");
					input = keyboard.nextLine();
					int nrOfBook = Integer.parseInt(input);
					deleteBook(booksFound[nrOfBook-1]);
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


    //Method findBooks returns the number of books with the title input.
    public int findBook(String input){
		int count=0;
		for (int i=0; i<indexBook; i++){
            if (books[i].getName().equals(input)){
                booksFound[count]=books[i];
				count++;
	        }
        }
		return count;
    }
	
	public int indexOf(Book b){
		for (int i=0; i<indexBook; i++){
			if (books[i].equals(b)){
				return i;
			}
		}
		return NO_INDEX;
		
	}

    public void deleteBook(Book book){
        
		indexBook = indexBook-1;
        for (int i=indexOf(book); i<indexBook; i++){
            books[i] = books[i+1];
        }
        books[indexBook]=null;
    }
   
    public void addBook(Book b){
		
		if (indexBook < books.length){
			for (int i=0; i<indexBook; i++){
				if (books[i].equals(b)){
					printBook(books[i]);
					printBook(b);
					System.out.println("The book can already be found in the library.");
					return;	
				}
			}
			books[indexBook] = b;
			indexBook++;			
		}
	}
    
    public void typeOfBook() {
        /* TODO - CODE REVIEW - This method is linked to adding a book.
        Let's say you'd want to use it in a search for example, you would need to change it.
        
        A better design would have been:
        typeOfBook returns the type of book and in the makeDecision() method you could have writtem:
        String bookType = inquireBookType();
        if (bookType.contains("novel")){
               Book b = readNovel();
               addBook(b);
        }
        else if (input.contains("art album")){
                Book b = readArtAlbum();
                addBook(b);
        }
        */
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
				printBook(books[i]);
        }
	}
        
    public void printBook(Book b){
    	if (b instanceof Novel) {
			Novel n = (Novel) b;
			System.out.println(n.getName()+" "+n.getNrPages()+" pages "+ n.getType());
		}
		if (b instanceof ArtAlbum){
			ArtAlbum a = (ArtAlbum) b;
			System.out.println(a.getName()+" "+ a.getNrPages()+" pages "+ a.getPaperQuality());
		}
	}
    
            
    public void listNovels(){
        System.out.println("The library contains the following novels: ");
        for (int i=0; i<indexBook; i++){
			if (books[i] instanceof Novel){
            	printBook(books[i]);
			}
		}
    }

    public void listArtAlbums(){
        System.out.println("The library contains the following art albums: ");
        for (int i=0; i<indexBook; i++){
			if(books[i] instanceof ArtAlbum){
            printBook(books[i]);
			}
		}
    }

    public void populateLibrary(){
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
        libr.populateLibrary();
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
		
		if ((name.equals(book.name)==false)){
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
		
		
		if (!(type.equals(novel.type))) {
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
		
		
		if (!(paperQuality.equals(artAlbum.paperQuality))) {
          return false;
        }
		
		return true;
    }
	

}