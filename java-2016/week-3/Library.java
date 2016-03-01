import java.util.Scanner;

public class Library {
	
	private Novel [] novels = new Novel[100];
	private ArtAlbum [] artAlbums = new ArtAlbum[100];
    private int indexNovel = 0;
    private int indexArtAlbum = 0;
        


public void makeDecision(){
    System.out.println("What do you want to do? (add book/delete book/list books ");
    Scanner keyboard = new Scanner(System.in);
    String input = keyboard.nextLine();
    if (input.equals("add book")) {
        typeOfBook();
       
    }
    if (input.equals("delete book")){
		
    
    }
    if (input.equals("list books")){
            listBooks(); 
    }
}

public void addNovel(Novel n){
    if (indexNovel <novels.length){
        novels[indexNovel] = n;
        indexNovel++;
    }

}

public void addArtAlbum(ArtAlbum a){
    if (indexArtAlbum <artAlbums.length){
        artAlbums[indexArtAlbum] = a;
        indexArtAlbum++;
    }

}

public void typeOfBook() {
    
    Scanner keyboard = new Scanner(System.in);
    System.out.print("Is it a novel or an art album? ");
        String input = keyboard.nextLine();
        System.out.println(input);
        if (input.contains("novel")){
           Novel n = readNovel();
           addNovel(n);
        }
        else if (input.contains("art album")){
                 ArtAlbum a = readArtAlbum();
                 addArtAlbum(a);
            }
        else {
            System.out.println("It has to be a novel or an art album");
            
        }
        
}

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
public ArtAlbum readArtAlbum(){
    Scanner reader = new Scanner(System.in);
    
    System.out.print("Enter the name of the art album ");
    String nameOfAlbum = reader.nextLine();
    System.out.print("Enter the number of pages ");
    String strNrPages = reader.nextLine();
    int nrOfPages = Integer.parseInt(strNrPages);
    
    System.out.print("Enter the paper quality of the art album ");
    String qualityOfPaper = reader.nextLine();
    
    ArtAlbum a = new ArtAlbum(nameOfAlbum, nrOfPages, qualityOfPaper);
    return a;
}
        
public void listBooks(){
    System.out.println("What type of books do you want to see?(novels/art albums) ");
        Scanner reader = new Scanner (System.in);
        String input = reader.nextLine();
        if (input.contains("novels")){
            listNovels();
        }
        else if (input.contains("art albums")){
            listArtAlbums();
        }
}
public void listNovels(){
    for (int i=0; i<indexNovel; i++){
        System.out.println(novels[i].getName()+" "+novels[i].getNrPages()+" "+novels[i].getType() );
    }

}

public void listArtAlbums(){
    for (int i=0; i<indexArtAlbum; i++){
        System.out.println(artAlbums[i].getName()+" "+artAlbums[i].getNrPages()+" "+artAlbums[i].getPaperQuality());
    }
}

public void populateArrays(){
    Novel novel1 = new Novel("Walden", 235, "fiction");
    addNovel(novel1);
    
    Novel novel2 = new Novel("Harry Potter: Prisoner of Azkaban", 556, "fantasy");
    addNovel(novel2);
    
    Novel novel3 = new Novel("1984", 242, "fiction");
    addNovel(novel3);
    
    Novel novel4 = new Novel("The Shining", 150, "horror");
    addNovel(novel4);
    
    ArtAlbum album1 = new ArtAlbum("Monet", 147, "high-quality");
    addArtAlbum(album1);
    
    ArtAlbum album2 = new ArtAlbum("History of photography", 490, "high-quality");
    addArtAlbum(album2);
    
    ArtAlbum album3 = new ArtAlbum("The works of Jackson Pollock", 67, "medium-quality");
    addArtAlbum(album3);
}

public static void main (String [] args){
	
    Library libr = new Library();
    libr.populateArrays();
    libr.makeDecision();
    libr.listNovels();
    
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
	private String type;
	
	public Novel (String name, int nrPages, String type){
		super(name, nrPages);
		this.type = type;
	} 
	
	public void setType(String newType){
		type = newType;
	}
	
	public String getType(){
		return type;
	}
}

class ArtAlbum extends Book {
	private String paperQuality;
	
	public ArtAlbum(String name, int nrPages, String paperQuality){
		super(name, nrPages);
		this.paperQuality=paperQuality;
	}
        
    public void setPaperQuality(String newPaperQuality){
        paperQuality = newPaperQuality;
    }
        
    public String getPaperQuality(){
        return paperQuality;
    }
	
}
