import java.util.Scanner;

public class Library {
	
	Novel [] novelArray;
	ArtAlbum [] artAlbumArray;

public Novel readNovel(){
    Scanner reader = new Scanner(System.in);
    
    System.out.print("Enter the name of the novel ");
    String nameOfNovel = reader.nextLine();
    System.out.print("Enter the number of pages ");
    String strNrPages = reader.nextLine();
    int nrOfPages = Integer.parseInt(strNrPages);
    
    System.out.print("Enter the type of the novel ");
    String typeOfNovel = reader.nextLine();
    
    Novel n = new Novel(nameOfNovel, nrOfPages, typeOfNovel);
    return n;
}
        
public void addNovel(int index){
    
    Novel n = readNovel();
    novelArray[index]=n;
    
} 


public static void main (String [] args){
	
    Library libr = new Library();
    String bookInsert="yes"; 
    int arrayPosition = 0;
    while (bookInsert=="yes"){
    System.out.println("Do you want to insert a book? ");
    Scanner reader = new Scanner(System.in);
    String input = reader.nextLine();
    
    if (input.equals("yes")){
        System.out.print("Is it a novel or an art album?");
        input = reader.nextLine();
        System.out.println(input);
        if (input.contains("novel")){
            libr.addNovel(arrayPosition);
            arrayPosition++;
        }
	
    }
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
}