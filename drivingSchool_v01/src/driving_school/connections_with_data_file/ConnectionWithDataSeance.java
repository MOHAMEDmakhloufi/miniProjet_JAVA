package driving_school.connections_with_data_file;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import driving_school.entites.Candidate;
import driving_school.entites.Instructor;
import driving_school.entites.Person;
import driving_school.entites.Seance;
import driving_school.entites.SeanceCode;
import driving_school.entites.SeanceDriving;


public class ConnectionWithDataSeance {
	
	public static void add(Seance seance){
		//read class name 
		String nameofClass = seance.getClass().getSimpleName();
		
		//JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
         
        try (FileReader reader = new FileReader(".\\src\\datafile\\"+nameofClass+".json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
 
            JSONArray seanceList = (JSONArray) obj;
           
            //New person
            JSONObject newSeance = creatJsonObject(seance);
            
            //add in arrayJSON
            JSONObject seanceObject= new JSONObject();
            seanceObject.put(nameofClass, newSeance);
            if(seanceList.add(seanceObject))
            	System.out.println("\t\t\t--> add successfully <--");
            
            //close file
            reader.close();
            //WriteJsonFile
            try(FileWriter file = new FileWriter(".\\src\\datafile\\"+nameofClass+".json")){
                file.write(seanceList.toJSONString());
                file.flush();
                 
            }catch(IOException e){
                e.printStackTrace();
            }
 
        } catch (FileNotFoundException e) {
            JSONArray seanceList = new JSONArray();
            
            //New person
            JSONObject newSeance = creatJsonObject(seance);
            
            //add in arrayJSON
            JSONObject seanceObject= new JSONObject();
            seanceObject.put(nameofClass, newSeance);
            if(seanceList.add(seanceObject))
            	System.out.println("\t\t\t--> add successfully <--");
            
            //WriteJsonFile
            try(FileWriter file = new FileWriter(".\\src\\datafile\\"+nameofClass+".json")){
                file.write(seanceList.toJSONString());
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
	public static boolean updateByDateAndIdInstructor(Date date, long idInstructor, Seance seance){
		long ind = -1;
		String nameofClass = seance.getClass().getSimpleName();
		
		boolean updateTest = true;
		//JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
         
        try (FileReader reader = new FileReader(".\\src\\datafile\\"+nameofClass+".json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
 
            JSONArray seanceList = (JSONArray) obj;
            
            //test if file empty or not
            if(seanceList.isEmpty())
           	 return false;
             
            //Iterate over Person array
            for(Object sea : seanceList) {
            	//get Person
            	JSONObject seanceObject = (JSONObject)sea;
            	seanceObject = (JSONObject)seanceObject.get(nameofClass);
            	//test
            	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            	String sDate= sdf.format(date);

            	if(((long)seanceObject.get("cinI") == idInstructor) && (((String)seanceObject.get("date")).equals(sDate)))
            		ind = seanceList.indexOf(sea);
            }	
            if (ind != -1 ){
        		//delete the old Person
        		seanceList.remove((int)ind);
        		//add new modification
                JSONObject newSeance = creatJsonObject(seance);
                
                //add in arrayJSON
                JSONObject seanceObj= new JSONObject();
                seanceObj.put(nameofClass, newSeance);
        		seanceList.add(seanceObj);
        	}
                //close file
                reader.close();
                //WriteJsonFile
                try(FileWriter file = new FileWriter(".\\src\\datafile\\"+nameofClass+".json")){
                    file.write(seanceList.toJSONString());
                    file.flush();
                    
                }catch(IOException e){
                    e.printStackTrace();
                }
            
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return updateTest;
	}
	/**
	 * creat new Vehicle Jsonobject
	 * @param Vehicle
	 * @return
	 */
	private static JSONObject creatJsonObject(Seance seance) {
		
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        JSONObject newSeance = new JSONObject();
        //newSeance.put("id", seance.getId());
        newSeance.put("cinI", seance.getCinI() );
        newSeance.put("cinC", seance.getCinC() );
        if(seance instanceof SeanceDriving)
        	newSeance.put("idVehicle",((SeanceDriving)seance).getIdVehicle());
        newSeance.put("date", sdf.format(seance.getDate()));

        return newSeance;
	}
	/**
	 * cast JSONObject to Seance
	 */
	private static Seance castJOtoSeance(JSONObject seanceObject, String nameOfClass) {
		Seance seance;
		if(nameOfClass.equals("SeanceDriving") )
			seance = new SeanceDriving(0, 0, null, 0);
		else 
			seance = new SeanceCode(0, 0, null);
		
		seance.setCinI((long)seanceObject.get("cinI"));
		seance.setCinC((long)seanceObject.get("cinC"));

		if(seance instanceof SeanceDriving)
			((SeanceDriving)seance).setIdVehicle((long)seanceObject.get("idVehicle"));

		String date = (String)seanceObject.get("date");
        try {
			Date date1=new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(date);
			seance.setDate(date1);
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}  
		
		return seance;
	}
	
	/**
	 * Delete Seance By date and Cin Instructor
	 */
	public static boolean delete(Date date, long cinI, String nameOfClass){
		
		long ind = -1;
		boolean deleteTest = false;
		//JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
         
        try (FileReader reader = new FileReader(".\\src\\datafile\\"+nameOfClass+".json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
 
            JSONArray seanceList = (JSONArray) obj;
            
            //test if file empty or not
            if(seanceList.isEmpty())
           	 return false;
             
            //Iterate over Seance array
            for(Object sea : seanceList) {
            	//get Person
            	JSONObject seanceObject = (JSONObject)sea;
            	seanceObject = (JSONObject)seanceObject.get(nameOfClass);
            	//test
            	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            	String sDate= sdf.format(date);
            	if(((long)seanceObject.get("cinI") == cinI) && (((String)seanceObject.get("date")).equals(sDate))) 
            		ind = seanceList.indexOf(sea);
            }
            if (ind != -1 ) {
            	deleteTest = true;
            	seanceList.remove((int)ind);
            }
            	
                //close file
                //reader.close();
                //WriteJsonFile
                try(FileWriter file = new FileWriter(".\\src\\datafile\\"+nameOfClass+".json")){
                    file.write(seanceList.toJSONString());
                    file.flush();
                    return deleteTest && true;
                }catch(IOException e){
                    e.printStackTrace();
                }
            
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
		return false;
	}
	
	
	/**
	 * search seance by date and cin Instructor
	 * @param date
	 * @param cinI
	 * @param nameOfClass
	 * @return
	 */
	public static Seance getByDateAndCinI(Date date, long cinI, String nameOfClass) {
		//JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
         
        try (FileReader reader = new FileReader(".\\src\\datafile\\"+nameOfClass+".json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
 
            JSONArray seanceList = (JSONArray) obj;
            
            //test if file empty or not
            if(seanceList.isEmpty())
           	 return null;
             
            //Iterate over Person array
            for(Object sea : seanceList) {
            	//get Person
            	JSONObject seanceObject = (JSONObject)sea;
            	seanceObject = (JSONObject)seanceObject.get(nameOfClass);
            	//test
            	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            	String sDate= sdf.format(date);
            	if(((long)seanceObject.get("cinI") == cinI) && (((String)seanceObject.get("date")).equals(sDate)))
            		return castJOtoSeance(seanceObject, nameOfClass);
            }
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
		return null;
	}
	/**
	 * with  a parameter idCandidate, return the seanceDriving or seanceCode
	 * @param cinCandidate
	 * @param nameofCalss
	 * @return
	 */
	public static ArrayList<Seance> getByIdCandidate(long cinCandidate, String nameofCalss){
		
		//declaration for ArrayList<Seance>
		ArrayList<Seance> arrayList = new ArrayList<Seance>();
		//JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
         
        try (FileReader reader = new FileReader(".\\src\\datafile\\"+nameofCalss+".json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
 
            JSONArray seanceList = (JSONArray) obj;
            
            //test if file empty or not
             if(seanceList.isEmpty())
            	 return null;
            //Iterate over Person array
            for(Object sea : seanceList) {
            	//get Seance 
            	JSONObject seanceObject = (JSONObject)sea;
            	seanceObject = (JSONObject)seanceObject.get(nameofCalss);
            	if((long)seanceObject.get("cinC") == cinCandidate)
            			arrayList.add(castJOtoSeance(seanceObject, nameofCalss));
            }
            return arrayList;
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
		return null;
	}
	
}
