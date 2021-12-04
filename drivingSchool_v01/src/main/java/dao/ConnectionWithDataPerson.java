package dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import entities.Person;
import entities.Candidate;
import entities.Instructor;


public class ConnectionWithDataPerson {
	

	
	public static void add(Person person){
		//read class name 
		String nameofClass = person.getClass().getSimpleName();
		
		//JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
         
        try (FileReader reader = new FileReader(".\\src\\main\\resources\\"+nameofClass+".json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
 
            JSONArray personList = (JSONArray) obj;
           
            //New person
            JSONObject newPerson = creatJsonObject(person);
            
            //add in arrayJSON
            JSONObject personObject= new JSONObject();
            personObject.put(nameofClass, newPerson);
            if(personList.add(personObject))
            	System.out.println("\t\t\t--> add successfully <--");
            
            //close file
            reader.close();
            //WriteJsonFile
            try(FileWriter file = new FileWriter(".\\src\\main\\resources\\"+nameofClass+".json")){
                file.write(personList.toJSONString());
                file.flush();
                 
            }catch(IOException e){
                e.printStackTrace();
            }
 
        } catch (FileNotFoundException e) {
            JSONArray personList = new JSONArray();
            
            //New person
            JSONObject newPerson = creatJsonObject(person);
            
            //add in arrayJSON
            JSONObject personObject= new JSONObject();
            personObject.put(nameofClass, newPerson);
            if(personList.add(personObject))
            	System.out.println("\t\t\t--> add successfully <--");
            
            //WriteJsonFile
            try(FileWriter file = new FileWriter(".\\src\\main\\resources\\"+nameofClass+".json")){
                file.write(personList.toJSONString());
                file.flush();
                 
            }catch(IOException exe){
                exe.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
			e.printStackTrace();
		} 
	}
	/**
	 * update Candidate By id
	 */
	public static boolean updateByCin(long cin, Person person){
		long ind = -1;
		String nameofClass = person.getClass().getSimpleName();
		
		boolean updateTest = true;
		//JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
         
        try (FileReader reader = new FileReader(".\\src\\main\\resources\\"+nameofClass+".json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
 
            JSONArray personList = (JSONArray) obj;
            
            //test if file empty or not
            if(personList.isEmpty())
           	 return false;
             
            //Iterate over Person array
            for(Object can : personList) {
            	//get Person
            	JSONObject personObject = (JSONObject)can;
            	personObject = (JSONObject)personObject.get(nameofClass);
            	//test
            	
            	if((long)personObject.get("cin") == cin)
            		ind = personList.indexOf(can);
            }	
            if (ind != -1 ){
        		//delete the old Person
        		personList.remove((int)ind);
        		//add new modification
                JSONObject newPerson = creatJsonObject(person);
                
                //add in arrayJSON
                JSONObject personObj= new JSONObject();
                personObj.put(nameofClass, newPerson);
        		personList.add(personObj);
        	}
                //close file
                reader.close();
                //WriteJsonFile
                try(FileWriter file = new FileWriter(".\\src\\main\\resources\\"+nameofClass+".json")){
                    file.write(personList.toJSONString());
                    file.flush();
                    
                }catch(IOException e){
                    e.printStackTrace();
                }
            
 
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return updateTest;
	}
	/**
	 * creat new Person Jsonobject
	 * @param Person
	 * @return
	 */
	private static JSONObject creatJsonObject(Person person) {
		
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        JSONObject newPerson = new JSONObject();
        newPerson.put("cin", person.getCin());
        newPerson.put("name", person.getName() );
        newPerson.put("phoneNumber", person.getPhoneNumber());
        newPerson.put("emailAddress", person.getEmailAddress());
        newPerson.put("dateOfBirthday", sdf.format(person.getDateOfBirthday()));
        newPerson.put("paidAmount", person.getPaidAmount());
        if(person instanceof Candidate)
        	newPerson.put("category",((Candidate)person).getCategory());
        return newPerson;
	}
	
	/**
	 * cast JSONObject to Person
	 */
	private static Person castJOtoCandidate(JSONObject personObject, String nameOfClass) {
		Person person;
		if(nameOfClass.equals("Candidate") )
			person= new Candidate(0, null, 0, null, null, null, 0);
		else 
			person= new Instructor(0, null, 0, null, null, 0);
		
		person.setCin((long)personObject.get("cin"));
		person.setName((String)personObject.get("name"));
		person.setPhoneNumber((long)personObject.get("phoneNumber"));
		person.setEmailAddress((String)personObject.get("emailAddress"));
		if(person instanceof Candidate)
			((Candidate)person).setCategory((String)personObject.get("category"));
		person.setPaidAmount((double)personObject.get("paidAmount"));
		String date = (String)personObject.get("dateOfBirthday");
        try {
			Date date1=new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(date);
			person.setDateOfBirthday(date1);
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}  
		
		return person;
	}
	/**
	 * get Person by cin
	 * @return 
	 */
	public static Person search(long cin, String nameofClass){
		
		
		
		//JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
         
        try (FileReader reader = new FileReader(".\\src\\main\\resources\\"+nameofClass+".json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
 
            JSONArray personList = (JSONArray) obj;
            
            //test if file empty or not
            if(personList.isEmpty())
           	 return null;
             
            //Iterate over Person array
            for(Object per : personList) {
            	//get Person
            	JSONObject personObject = (JSONObject)per;
            	personObject = (JSONObject)personObject.get(nameofClass);
            	//test
            	if((long)personObject.get("cin") == cin)
            		return castJOtoCandidate(personObject, nameofClass);
            }
 
        } catch (FileNotFoundException e) {
        	System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
		return null;
	}
	/**
	 * getAll Persons
	 */
	public static ArrayList<Person> getAll(String nameofClass){
		
		
		//declaration for ArrayList<Person>
		ArrayList<Person> arrayList = new ArrayList<Person>();
		//JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
         
        try (FileReader reader = new FileReader(".\\src\\main\\resources\\"+nameofClass+".json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
 
            JSONArray personList = (JSONArray) obj;
            
            //test if file empty or not
             if(personList.isEmpty())
            	 return arrayList;
            //Iterate over Person array
            for(Object per : personList) {
            	//get Person
            	JSONObject personObject = (JSONObject)per;
            	personObject = (JSONObject)personObject.get(nameofClass);
            	
            	arrayList.add(castJOtoCandidate(personObject, nameofClass));
            }
            return arrayList;
 
        } catch (FileNotFoundException e) {
        	System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
		return arrayList;
	}
	
	/**
	 * Delete Person By Cin
	 */
	public static boolean delete(long cin, String nameofClass){
		
		long ind = -1;
		boolean deleteTest = false;
		//JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
         
        try (FileReader reader = new FileReader(".\\src\\main\\resources\\"+nameofClass+".json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
 
            JSONArray personList = (JSONArray) obj;
            
            //test if file empty or not
            if(personList.isEmpty())
           	 return false;
             
            //Iterate over Person array
            for(Object per : personList) {
            	//get Person
            	JSONObject personObject = (JSONObject)per;
            	personObject = (JSONObject)personObject.get(nameofClass);
            	//test
            	
            	if((long)personObject.get("cin") == cin) 
            		ind = personList.indexOf(per);
            }
            if (ind != -1 ) {
            	deleteTest = true;
            	personList.remove((int)ind);
            }
            	
                //close file
                //reader.close();
                //WriteJsonFile
                try(FileWriter file = new FileWriter(".\\src\\main\\resources\\"+nameofClass+".json")){
                    file.write(personList.toJSONString());
                    file.flush();
                    return deleteTest && true;
                }catch(IOException e){
                    e.printStackTrace();
                }
            
 
        } catch (FileNotFoundException e) {
        	System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
		return false;
	}
}
