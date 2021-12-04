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

import entities.Car;
import entities.Moto;
import entities.Truck;
import entities.Vehicle;



public class ConnectionWithDataVehicle {
	
	public static void add(Vehicle vehicle){
		//read class name 
		String nameofClass = vehicle.getClass().getSimpleName();
		
		//JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
         
        try (FileReader reader = new FileReader(".\\src\\main\\resources\\"+nameofClass+".json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
 
            JSONArray vehicleList = (JSONArray) obj;
           
            //New person
            JSONObject newVehicle = creatJsonObject(vehicle);
            
            //add in arrayJSON
            JSONObject vehicleObject= new JSONObject();
            vehicleObject.put(nameofClass, newVehicle);
            if(vehicleList.add(vehicleObject))
            	System.out.println("\t\t\t--> add successfully <--");
            
            //close file
            reader.close();
            //WriteJsonFile
            try(FileWriter file = new FileWriter(".\\src\\main\\resources\\"+nameofClass+".json")){
                file.write(vehicleList.toJSONString());
                file.flush();
                 
            }catch(IOException e){
                e.printStackTrace();
            }
 
        } catch (FileNotFoundException e) {
            JSONArray vehicleList = new JSONArray();
            
            //New person
            JSONObject newVehicle = creatJsonObject(vehicle);
            
            //add in arrayJSON
            JSONObject vehicleObject= new JSONObject();
            vehicleObject.put(nameofClass, newVehicle);
            if(vehicleList.add(vehicleObject))
            	System.out.println("\t\t\t--> add successfully <--");
            
            //WriteJsonFile
            try(FileWriter file = new FileWriter(".\\src\\main\\resources\\"+nameofClass+".json")){
                file.write(vehicleList.toJSONString());
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
	 * update Vehicle By id
	 */
	public static boolean updateById(long id, Vehicle vehicle){
		long ind = -1;
		String nameofClass = vehicle.getClass().getSimpleName();
		
		boolean updateTest = true;
		//JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
         
        try (FileReader reader = new FileReader(".\\src\\main\\resources\\"+nameofClass+".json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
 
            JSONArray vehicleList = (JSONArray) obj;
            
            //test if file empty or not
            if(vehicleList.isEmpty())
           	 return false;
             
            //Iterate over Vehicle array
            for(Object veh : vehicleList) {
            	//get vehicle
            	JSONObject vehicleObject = (JSONObject)veh;
            	vehicleObject = (JSONObject)vehicleObject.get(nameofClass);
            	//test
            	if (vehicleObject != null)
            		if((long)vehicleObject.get("id") == id)
            			ind =vehicleList.indexOf(veh);
            }
            if (ind != -1) {
        		//delete the old vehicle
        		vehicleList.remove((int)ind);
        		//add new modification
                JSONObject newVehicle = creatJsonObject(vehicle);
                
                //add in arrayJSON
                JSONObject vehicleObj= new JSONObject();
                vehicleObj.put(nameofClass, newVehicle);
        		vehicleList.add(vehicleObj);
        		}
                //close file
                reader.close();
                //WriteJsonFile
                try(FileWriter file = new FileWriter(".\\src\\main\\resources\\"+nameofClass+".json")){
                    file.write(vehicleList.toJSONString());
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
	 * creat new Vehicle Jsonobject
	 * @param Vehicle
	 * @return
	 */
	private static JSONObject creatJsonObject(Vehicle vehicle) {
		
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        JSONObject newVehicle = new JSONObject();
        newVehicle.put("id", vehicle.getId());
        newVehicle.put("name", vehicle.getName() );
        newVehicle.put("theDateOfPurchase", sdf.format(vehicle.getTheDateOfPurchase()));

        return newVehicle;
	}
	
	/**
	 * cast JSONObject to Vehicle
	 */
	private static Vehicle castJOtoVehicle(JSONObject vehicleObject, String nameOfClass) {
		Vehicle vehicle;
		if(nameOfClass.equals("Car") )
			vehicle= new Car(0, null, null);
		else if (nameOfClass.equals("Moto") )
			vehicle= new Moto(0, null, null);
		else 
			vehicle= new Truck(0, null, null);
		
		vehicle.setId((long)vehicleObject.get("id"));
		vehicle.setName((String)vehicleObject.get("name"));
		
		String date = (String)vehicleObject.get("theDateOfPurchase");
        try {
			Date date1=new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(date);
			vehicle.setTheDateOfPurchase(date1);
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}  
		
		return vehicle;
	}
	/**
	 * get Vehicle by Id
	 * @return 
	 */
	public static Vehicle search(long id, String nameofClass){
		
		
		
		//JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
         
        try (FileReader reader = new FileReader(".\\src\\main\\resources\\"+nameofClass+".json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
 
            JSONArray vehicleList = (JSONArray) obj;
            
            //test if file empty or not
            if(vehicleList.isEmpty())
           	 return null;
             
            //Iterate over Vehicle array
            for(Object veh : vehicleList) {
            	//get Vehicle
            	JSONObject vehicleObject = (JSONObject)veh;
            	vehicleObject = (JSONObject)vehicleObject.get(nameofClass);
            	//test
            	if (vehicleObject != null)
            		if((long)vehicleObject.get("id") == id)
            			return castJOtoVehicle(vehicleObject, nameofClass);
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
	 * getAll Vehicles
	 */
	public static ArrayList<Vehicle> getAll(String nameofClass){
		
		
		//declaration for ArrayList<Vehicle>
		ArrayList<Vehicle> arrayList = new ArrayList<Vehicle>();
		//JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
         
        try (FileReader reader = new FileReader(".\\src\\main\\resources\\"+nameofClass+".json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
 
            JSONArray vehicleList = (JSONArray) obj;
            
            //test if file empty or not
             if(vehicleList.isEmpty())
            	 return arrayList;
            //Iterate over Person array
            for(Object veh : vehicleList) {
            	//get vehicle
            	JSONObject vehicleObject = (JSONObject)veh;
            	vehicleObject = (JSONObject)vehicleObject.get(nameofClass);
            	if (vehicleObject != null) 
            		arrayList.add(castJOtoVehicle(vehicleObject, nameofClass));
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
	public static boolean delete(long id, String nameofClass){
		
		long ind = 0;
		boolean deleteTest = false;
		//JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
         
        try (FileReader reader = new FileReader(".\\src\\main\\resources\\"+nameofClass+".json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
 
            JSONArray vehicleList = (JSONArray) obj;
            
            //test if file empty or not
            if(vehicleList.isEmpty())
           	 return false;
             
            //Iterate over Person array
            for(Object veh : vehicleList) {
            	//get Person
            	JSONObject vehicleObject = (JSONObject)veh;
            	vehicleObject = (JSONObject)vehicleObject.get(nameofClass);
            	//test
            	
            	if (vehicleObject != null) {
            		if((long)vehicleObject.get("id") == id) 
            			 ind = vehicleList.indexOf(veh);
            	}	
            }
            if (ind != -1 ) {
            	deleteTest = true;
            	vehicleList.remove((int)ind);
            }
            	
           
                //close file
                //reader.close();
                //WriteJsonFile
                try(FileWriter file = new FileWriter(".\\src\\main\\resources\\"+nameofClass+".json")){
                    file.write(vehicleList.toJSONString());
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
