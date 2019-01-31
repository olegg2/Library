package ua.logos;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.modelmapper.internal.util.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cglib.transform.impl.AddDelegateTransformer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;

import ua.logos.entity.AuthorEntity;
import ua.logos.entity.BookEntity;
import ua.logos.entity.DescriptionEntity;
import ua.logos.entity.GenreEntity;
import ua.logos.entity.RatingEntity;
import ua.logos.entity.RoleEntity;
import ua.logos.entity.TagEntity;
import ua.logos.entity.UserEntity;
import ua.logos.exception.ResourceNotFoundException;
import ua.logos.repository.AuthorRepository;
import ua.logos.repository.BookRepository;
import ua.logos.repository.DescriptionRepository;
import ua.logos.repository.GenreRepository;
import ua.logos.repository.RatingRepository;
import ua.logos.repository.RoleRepository;
import ua.logos.repository.TagRepository;
import ua.logos.repository.UserRepository;
import ua.logos.utils.ObjectMapperUtils;
import ua.logos.utils.StringUtils;




@SpringBootApplication
public class LibraryProjectApplication implements CommandLineRunner {

	public static void main(String[] args) throws FileNotFoundException, IOException {
	ConfigurableApplicationContext context = SpringApplication.run(LibraryProjectApplication.class, args);
	//System.out.println(41/10);
		addGenres(context);
		addAuthors(context);
		addDescriptions(context);
		addTags(context);
		addRatings(context);
		addRandomBooks(context, 32);
		
		
	}
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public void run(String... args) throws Exception {
		if(roleRepository.count() == 0) {
			RoleEntity entity = new RoleEntity();
			entity.setName("ADMIN");
			
			roleRepository.save(entity);
			
			entity = new RoleEntity();
			entity.setName("USER");
			
			roleRepository.save(entity);
		
			
		}
		if(userRepository.count() == 0) {
			UserEntity user = new UserEntity();
			user.setUsername("admin");
			user.setPassword(passwordEncoder.encode("1234"));
			
			RoleEntity role = roleRepository.findByName("ADMIN")
					.orElseThrow( 
					() -> new ResourceNotFoundException("Role not found")
					 
					);
			
			Set<RoleEntity> roles = new HashSet<>();
			roles.add(role);
			user.setRoles(roles);
			
			userRepository.save(user);
		}
		
	}
	
	
	
	/////////////////////////////////////////////
	//add genre +
	public static void addGenres(ConfigurableApplicationContext context) {
		List<String> genres= new ArrayList<>();
		genres.add("action");
		genres.add("fantasy");
		genres.add("science fiction");
		genres.add("comedy");
		genres.add("horror");
		genres.add("drama");
		StringUtils stringUtils = context.getBean(StringUtils.class);
		GenreRepository genreRepository = context.getBean(GenreRepository.class);
		if(genreRepository.count()==0) {
			
			genres.forEach(c ->{
				GenreEntity entity = new GenreEntity();
				entity.setGenreId(stringUtils.generate());
				entity.setNameOfGenre(c);
				genreRepository.save(entity);
				
			});
		}
			
	}
	//add tags
	public static void addTags(ConfigurableApplicationContext context) {
		List<String> tags = new ArrayList<>();
		tags.add("adopted protaonist");
		tags.add("aliens");
		tags.add("betrayal");
		tags.add("cunning protagonist");
		tags.add("time progretion");
		tags.add("magic");
		tags.add("matrial arts");
		tags.add("revenge");
		tags.add("Lucky protagonist");
		tags.add("modern time");
		StringUtils stringUtils = context.getBean(StringUtils.class);
		TagRepository tagRepository = context.getBean(TagRepository.class);
		if(tagRepository.count()==0) {
			tags.forEach(t->{
				TagEntity entity = new TagEntity();
				entity.setTagId(stringUtils.generate());
				entity.setNameOfTag(t);
				tagRepository.save(entity);
			});
		}
		
	}
	//add authors +
	public static void addAuthors(ConfigurableApplicationContext context) {
		List<String> authors= new ArrayList<>();
		authors.add("Joan Rouling");
		authors.add("I eat tomatoes");
		authors.add("Luk Besson");
		authors.add("Cristofer Paolini");
		authors.add("The Plagiarist");
		authors.add("Nayi ZiWenzi");
		
		AuthorRepository authorRepository = context.getBean(AuthorRepository.class);
		StringUtils stringUtils = context.getBean(StringUtils.class);
		if(authorRepository.count() ==0) {
			authors.forEach(a ->{
				AuthorEntity entity = new AuthorEntity();
				entity.setNameOfAuthor(a);
				entity.setAuthorId(stringUtils.generate());
				entity.setEmail(entity.getNameOfAuthor()+"@gmail.com");
				authorRepository.save(entity);
			});
		}
		}
	//add ratings
	public static void addRatings(ConfigurableApplicationContext context) {
		
		Map<Integer,String> ratings = new HashMap<>();
		ratings.put(1,"awful");
		ratings.put(2,"bad");
		ratings.put(3,"not that bad");
		ratings.put(4,"good");
		ratings.put(5,"great");
		
		RatingRepository ratingRepository = context.getBean(RatingRepository.class);
		int i=1;
		if(ratingRepository.count()==0) {
			for(Map.Entry<Integer,String> entry: ratings.entrySet()){
				RatingEntity entity = new RatingEntity();
				entity.setNumberOfRating(entry.getKey());
				entity.setTitleOfRating(entry.getValue());
				ratingRepository.save(entity);
				
			}
		}
	}
	//add description
	public static void addDescriptions(ConfigurableApplicationContext context) throws FileNotFoundException, IOException {
		DescriptionRepository descriptionRepository = context.getBean(DescriptionRepository.class);
		StringUtils stringUtils = context.getBean(StringUtils.class);
		List<String> descriptions = new ArrayList<>();
		Path path= null;
		
		try{
			 path = Paths.get(ClassLoader.getSystemResource("Descriptions").toURI());
			System.out.println(path.toString());
		}catch (Exception e) {
			System.out.println("not found Descriptions");
		}
		//
		try(FileReader reader = new FileReader(path.toString())){
			int count=0;
			int count2=0;
			int t=0;
			char[] arr = new char[255];
			int i=0;
			while((t=reader.read())!=-1) {
				
				if((char) t!='~') {
					arr[i]=(char) t;
					i++;
				}
				else {
					count2=i;
					descriptions.add(String.valueOf(arr).substring(0, count2));
					System.out.println(descriptions.get(count).length());
					arr = new char[255];
					i=0;
					count++;
					
				}
			}
			///if(arr.length!=0)
			//descriptions.add(String.valueOf(arr));
			
		}catch (FileNotFoundException e) {
			System.out.println("nema failu");
		}catch (Exception e) {
			System.out.println("pomulka");
		}
		System.out.println("Descriptions:");
		for (String string : descriptions) {
			System.out.println(string);
		}
		///////////////////////////
		if(descriptionRepository.count() ==0) {
			descriptions.forEach(d ->{
				DescriptionEntity entity = new DescriptionEntity();
				entity.setDescriptionId(stringUtils.generate());
				entity.setDescriptions(d);
				descriptionRepository.save(entity);
			});
			
			
		}
		//System.out.println(descriptionRepository.findById(1L).get().getDescriptions());
	
	}
	
	public static void addRandomBooks(ConfigurableApplicationContext context,int num) {
		BookRepository bookRepository = context.getBean(BookRepository.class);
		TagRepository tagRepository = context.getBean(TagRepository.class);
		RatingRepository ratingRepository = context.getBean(RatingRepository.class);
		if(bookRepository.count()==0) {
		StringUtils stringUtils = context.getBean(StringUtils.class);
		for (int i=0;i<num;i++) {
			BookEntity book = new BookEntity();
			book.setImageUrl("https://res.cloudinary.com/cthulhu/image/upload/v1544904206/books/noBook.jpg");
			book.setTitle("Book "+i);
			book.setBookId(stringUtils.generate());
			book.setYear(i);
			book.setNumberOfPages(i);
			//book.setImageUrl("www.url.com/"+i);
			//
			GenreEntity genre = new GenreEntity();
			if(i%2==0) {
				genre.setId(1L);
				book.setGenre(genre);
			}
			if(i%3==0) {
				genre.setId(2L);
				book.setGenre(genre);
			}
			if(i%7==0) {
				genre.setId(3L);
				book.setGenre(genre);
			}
			else {
				genre.setId(4L);
				book.setGenre(genre);
			}
			//
			
			AuthorEntity author = new AuthorEntity();
			if(i<10) {
				author.setAuthorId(stringUtils.generate());
				author.setId(1L);
			}
			else {
				author.setAuthorId(stringUtils.generate());
				author.setId(2L);
			}
			book.setAuthor(author);
			//
			List<TagEntity> tags= new ArrayList<TagEntity>();
			
			Random rand = new Random();
			for(int j =0;j<5;j++) {
			TagEntity tag = new TagEntity();
			int number = rand.nextInt((int)tagRepository.count()-1)+1;
			//System.out.println(number);
			Long number2 = (long) number;
			tag.setId(number2);
			tags.add(tag);
			}
			book.setTags(tags);
			
			//
			DescriptionEntity description = new DescriptionEntity();
			description.setId(1L);
			//System.out.println(description.getDescriptions());
			book.setDescription(description);
			//System.out.println(book.getDescription().getDescriptions());
			///////////////////
			RatingEntity rating = new RatingEntity();
			//Random rand2 = new Random();
			int number = rand.nextInt((int)ratingRepository.count()-1)+1;
			Long number2 = (long) number;
			rating.setId(number2);
			book.setRating(rating);
			
			bookRepository.save(book);
			
		}
		
	}
	}
	

}
