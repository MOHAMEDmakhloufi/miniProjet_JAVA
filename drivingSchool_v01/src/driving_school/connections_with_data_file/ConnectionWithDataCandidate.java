package driving_school.connections_with_data_file;

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

import driving_school.entites.Candidate;


public class ConnectionWithDataCandidate {
	
	
	public static void add(Candidate candidate){
		
		//JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
         
        try (FileReader reader = new FileReader(".\\src\\datafile\\candidate.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
 
            JSONArray candidateList = (JSONArray) obj;
           
            //New candidate
            JSONObject newCandidate = creatJsonObject(candidate);
            
            //add in arrayJSON
            JSONObject candidateObject= new JSONObject();
            candidateObject.put("candidate", newCandidate);
            if(candidateList.add(candidateObject))
            	System.out.println("\t\t\t--> add successfully <--");
            
            //close file
            reader.close();
            //WriteJsonFile
            try(FileWriter file = new FileWriter(".\\src\\datafile\\candidate.json")){
                file.write(candidateList.toJSONString());
                file.flush();
                 
            }catch(IOException e){
                e.printStackTrace();
            }
 
        } catch (FileNotFoundException e) {
            JSONArray candidateList = new JSONArray();
            
            //New candidate
            JSONObject newCandidate = creatJsonObject(candidate);
            
            //add in arrayJSON
            JSONObject candidateObject= new JSONObject();
            candidateObject.put("candidate", newCandidate);
            if(candidateList.add(candidateObject))
            	System.out.println("\t\t\t--> add successfully <--");
            
            //WriteJsonFile
            try(FileWriter file = new FileWriter(".\\src\\datafile\\candidate.json")){
                file.write(candidateList.toJSONString());
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
	public static boolean updateByCin(long cin, Candidate candidate ) {
		boolean updateTest = true;
		//JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
         
        try (FileReader reader = new FileReader(".\\src\\datafile\\candidate.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
 
            JSONArray candidateList = (JSONArray) obj;
            
            //test if file empty or not
            if(candidateList.isEmpty())
           	 return false;
             
            //Iterate over candidate array
            for(Object can : candidateList) {
            	//get candidate
            	JSONObject candidateObject = (JSONObject)can;
            	candidateObject = (JSONObject)candidateObject.get("candidate");
            	//test
            	
            	if((long)candidateObject.get("cin") == cin) {
            		//delete the old condidate
            		candidateList.remove(can);
            		//add new modification
                    JSONObject newCandidate = creatJsonObject(candidate);
                    
                    //add in arrayJSON
                    JSONObject candidateObj= new JSONObject();
                    candidateObj.put("candidate", newCandidate);
            		candidateList.add(candidateObj);
            	}
                //close file
                reader.close();
                //WriteJsonFile
                try(FileWriter file = new FileWriter(".\\src\\datafile\\candidate.json")){
                    file.write(candidateList.toJSONString());
                    file.flush();
                    
                }catch(IOException e){
                    e.printStackTrace();
                }
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
	 * creat new Candidate Jsonobject
	 * @param candidate
	 * @return
	 */
	private static JSONObject creatJsonObject(Candidate candidate) {
		
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        JSONObject newCandidate = new JSONObject();
        newCandidate.put("cin", candidate.getCin());
        newCandidate.put("name", candidate.getName() );
        newCandidate.put("phoneNumber", candidate.getPhoneNumber());
        newCandidate.put("emailAddress", candidate.getEmailAddress());
        newCandidate.put("dateOfBirthday", sdf.format(candidate.getDateOfBirthday()));
        newCandidate.put("category", candidate.getCategory());
        newCandidate.put("paidAmount", candidate.getPaidAmount());
        return newCandidate;
	}
	
	/**
	 * cast JSONObject to Candidate
	 */
	private static Candidate castJOtoCandidate(JSONObject candidateObject) {
		Candidate candidate= new Candidate(0, null, 0, null, null, null, 0);
		candidate.setCin((long)candidateObject.get("cin"));
		candidate.setName((String)candidateObject.get("name"));
		candidate.setPhoneNumber((long)candidateObject.get("phoneNumber"));
		candidate.setEmailAddress((String)candidateObject.get("emailAddress"));
		candidate.setCategory((String)candidateObject.get("category"));
		candidate.setPaidAmount((double)candidateObject.get("paidAmount"));
		String date = (String)candidateObject.get("dateOfBirthday");
        try {
			Date date1=new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(date);
			candidate.setDateOfBirthday(date1);
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}  
		
		return candidate;
	}
	/**
	 * get candidate by cin
	 * @return 
	 */
	public static Candidate search(long cin) {
		
		//JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
         
        try (FileReader reader = new FileReader(".\\src\\datafile\\candidate.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
 
            JSONArray candidateList = (JSONArray) obj;
            
            //test if file empty or not
            if(candidateList.isEmpty())
           	 return null;
             
            //Iterate over candidate array
            for(Object can : candidateList) {
            	//get candidate
            	JSONObject candidateObject = (JSONObject)can;
            	candidateObject = (JSONObject)candidateObject.get("candidate");
            	//test
            	if((long)candidateObject.get("cin") == cin)
            		return castJOtoCandidate(candidateObject);
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
	 * getAll candidates
	 */
	public static ArrayList<Candidate> getAll(){
		//declaration for ArrayList<Candidate>
		ArrayList<Candidate> arrayList = new ArrayList<Candidate>();
		//JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
         
        try (FileReader reader = new FileReader(".\\src\\datafile\\candidate.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
 
            JSONArray candidateList = (JSONArray) obj;
            
            //test if file empty or not
             if(candidateList.isEmpty())
            	 return null;
            //Iterate over candidate array
            for(Object can : candidateList) {
            	//get candidate
            	JSONObject candidateObject = (JSONObject)can;
            	candidateObject = (JSONObject)candidateObject.get("candidate");
            	
            	arrayList.add(castJOtoCandidate(candidateObject));
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
	
	/**
	 * Delete Candidate By Cin
	 */
	public static boolean delete(long cin) {
		boolean deleteTest = false;
		//JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
         
        try (FileReader reader = new FileReader(".\\src\\datafile\\candidate.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
 
            JSONArray candidateList = (JSONArray) obj;
            
            //test if file empty or not
            if(candidateList.isEmpty())
           	 return false;
             
            //Iterate over candidate array
            for(Object can : candidateList) {
            	//get candidate
            	JSONObject candidateObject = (JSONObject)can;
            	candidateObject = (JSONObject)candidateObject.get("candidate");
            	//test
            	
            	if((long)candidateObject.get("cin") == cin) 
            		deleteTest= candidateList.remove(can);

                //close file
                reader.close();
                //WriteJsonFile
                try(FileWriter file = new FileWriter(".\\src\\datafile\\candidate.json")){
                    file.write(candidateList.toJSONString());
                    file.flush();
                    return deleteTest && true;
                }catch(IOException e){
                    e.printStackTrace();
                }
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
}
