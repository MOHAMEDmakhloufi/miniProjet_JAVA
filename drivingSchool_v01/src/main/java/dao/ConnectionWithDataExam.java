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

import entities.Exam;
import entities.ExamCode;
import entities.ExamDriving;


public class ConnectionWithDataExam {
	/**
	 * 
	 * @param exam
	 */
	public static void add(Exam exam){
		//read class name 
		String nameofClass = exam.getClass().getSimpleName();
		
		//JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
         
        try (FileReader reader = new FileReader(".\\src\\main\\resources\\"+nameofClass+".json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
 
            JSONArray examList = (JSONArray) obj;
           
            //New Exam
            JSONObject newExam = creatJsonObject(exam);
            
            //add in arrayJSON
            JSONObject examObject= new JSONObject();
            examObject.put(nameofClass, newExam);
            if(examList.add(examObject))
            	System.out.println("\t\t\t--> add successfully <--");
            
            //close file
            reader.close();
            //WriteJsonFile
            try(FileWriter file = new FileWriter(".\\src\\main\\resources\\"+nameofClass+".json")){
                file.write(examList.toJSONString());
                file.flush();
                 
            }catch(IOException e){
                e.printStackTrace();
            }
 
        } catch (FileNotFoundException e) {
            JSONArray examList = new JSONArray();
            
            //New Exam
            JSONObject newExam = creatJsonObject(exam);
            
            //add in arrayJSON
            JSONObject seanceObject= new JSONObject();
            seanceObject.put(nameofClass, newExam);
            if(examList.add(seanceObject))
            	System.out.println("\t\t\t--> add successfully <--");
            
            //WriteJsonFile
            try(FileWriter file = new FileWriter(".\\src\\main\\resources\\"+nameofClass+".json")){
                file.write(examList.toJSONString());
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
	 * update Exam ByCin
	 * @param cinC
	 * @param exam
	 * @return
	 */
	public static boolean updateByCin(long cinC, Exam exam){
		long ind = -1;
		String nameofClass = exam.getClass().getSimpleName();
		
		boolean updateTest = true;
		//JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
         
        try (FileReader reader = new FileReader(".\\src\\main\\resources\\"+nameofClass+".json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
 
            JSONArray examList = (JSONArray) obj;
            
            //test if file empty or not
            if(examList.isEmpty())
           	 return false;
             
            //Iterate over Exam array
            for(Object ex : examList) {
            	//get Exam
            	JSONObject examObject = (JSONObject)ex;
            	examObject = (JSONObject)examObject.get(nameofClass);

            	if((long)examObject.get("cinC") == cinC)
            		ind = examList.indexOf(ex);
            }	
            if (ind != -1 ){
        		//delete the old Person
        		examList.remove((int)ind);
        		//add new modification
        		
                JSONObject newExam = creatJsonObject(exam);
                
                //add in arrayJSON
                JSONObject examObj= new JSONObject();
                examObj.put(nameofClass, newExam);
        		examList.add(examObj);
        	}
                //close file
                reader.close();
                //WriteJsonFile
                try(FileWriter file = new FileWriter(".\\src\\main\\resources\\"+nameofClass+".json")){
                    file.write(examList.toJSONString());
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
	 * creat new Exam Jsonobject
	 * @param exam
	 * @return
	 */
	private static JSONObject creatJsonObject(Exam exam) {
		
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        JSONObject newExam = new JSONObject();
        
        
        newExam.put("validation", exam.getValidation() );
        newExam.put("cinC", exam.getCinCandidate() );
        if(exam instanceof ExamDriving) {
        	newExam.put("cinI", ((ExamDriving)exam).getCinInstructor() );
        	newExam.put("idVehicle",((ExamDriving)exam).getIdVehicle());
        }
        	
        newExam.put("date", sdf.format(exam.getDate()));

        return newExam;
	}
	/**
	 * cast JSONObject to Exam
	 * @param examObject
	 * @param nameOfClass
	 * @return
	 */
	private static Exam castJOtoExam(JSONObject examObject, String nameOfClass) {
		Exam exam;
		if(nameOfClass.equals("ExamDriving") )
			exam = new ExamDriving(0, false, null, 0, 0);
		else 
			exam = new ExamCode(0, false, null);
		
		exam.setCinCandidate((long)examObject.get("cinC"));
		exam.setValidation((boolean)examObject.get("validation"));
		if(exam instanceof ExamDriving) {
			((ExamDriving)exam).setCinInstructor((long)examObject.get("cinI"));
			((ExamDriving)exam).setIdVehicle((long)examObject.get("idVehicle"));
		}
			
		String date = (String)examObject.get("date");
        try {
			Date date1=new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(date);
			exam.setDate(date1);
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}  
		
		return exam;
	}
	
	/**+
	 * delete Exam by cinCandidate
	 * @param cinC
	 * @param nameOfClass
	 * @return
	 */
	public static boolean delete(long cinC, String nameOfClass){
		
		long ind = -1;
		boolean deleteTest = false;
		//JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
         
        try (FileReader reader = new FileReader(".\\src\\main\\resources\\"+nameOfClass+".json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
 
            JSONArray examList = (JSONArray) obj;
            
            //test if file empty or not
            if(examList.isEmpty())
           	 return false;
             
            //Iterate over Exam array
            for(Object ex : examList) {
            	//get Exam
            	JSONObject examObject = (JSONObject)ex;
            	examObject = (JSONObject)examObject.get(nameOfClass);
            	//test
            	if((long)examObject.get("cinC") == cinC) 
            		ind = examList.indexOf(ex);
            }
            if (ind != -1 ) {
            	deleteTest = true;
            	examList.remove((int)ind);
            }
            	
                //close file
                //reader.close();
                //WriteJsonFile
                try(FileWriter file = new FileWriter(".\\src\\main\\resources\\"+nameOfClass+".json")){
                    file.write(examList.toJSONString());
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
	
	
	/**
	 * search by cin candidate
	 * @param cinC
	 * @param nameOfClass
	 * @return
	 */
	public static Exam search(long cinC, String nameOfClass) {
		//JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
         
        try (FileReader reader = new FileReader(".\\src\\main\\resources\\"+nameOfClass+".json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
 
            JSONArray examList = (JSONArray) obj;
            
            //test if file empty or not
            if(examList.isEmpty())
           	 return null;
             
            //Iterate over Exam array
            for(Object ex : examList) {
            	//get Exam
            	JSONObject examObject = (JSONObject)ex;
            	examObject = (JSONObject)examObject.get(nameOfClass);
            	//test
            	if((long)examObject.get("cinC") == cinC)
            		return castJOtoExam(examObject, nameOfClass);
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
	 * with  a parameter idInstructor, return the ExamDriving or ExamCode
	 * @param cinInstructor
	 * @param nameofCalss
	 * @return
	 */
	public static ArrayList<Exam> getByIdInstructor(long cinInstructor, String nameofCalss){
		
		//declaration for ArrayList<Exam>
		ArrayList<Exam> arrayList = new ArrayList<Exam>();
		//JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
         
        try (FileReader reader = new FileReader(".\\src\\main\\resources\\"+nameofCalss+".json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
 
            JSONArray examList = (JSONArray) obj;
            
            //test if file empty or not
             if(examList.isEmpty())
            	 return arrayList;
            //Iterate over Exam array
            for(Object ex : examList) {
            	//get Exam
            	JSONObject examObject = (JSONObject)ex;
            	examObject = (JSONObject)examObject.get(nameofCalss);
            	if((long)examObject.get("cinI") == cinInstructor)
            			arrayList.add(castJOtoExam(examObject, nameofCalss));
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
	 * with  a parameter idVehicle, return the ExamDriving 
	 * @param idVehicle
	 * @return
	 */
	public static ArrayList<Exam> getByIdVehicle(long idVehicle){
		
		//declaration for ArrayList<exam>
		ArrayList<Exam> arrayList = new ArrayList<Exam>();
		//JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
         
        try (FileReader reader = new FileReader(".\\src\\main\\resources\\SeanceDriving.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
 
            JSONArray examList = (JSONArray) obj;
            
            //test if file empty or not
             if(examList.isEmpty())
            	 return arrayList;
            //Iterate over Exam array
            for(Object ex : examList) {
            	//get Exam
            	JSONObject examObject = (JSONObject)ex;
            	examObject = (JSONObject)examObject.get("SeanceDriving");
            	if((long)examObject.get("idVehicle") == idVehicle)
            			arrayList.add(castJOtoExam(examObject, "SeanceDriving"));
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
	 * with a parameters dateDay, return the ExamDriving or ExamCode
	 * @param idVehicle
	 * @return
	 */
	public static ArrayList<Exam> getByDateDay(Date dateDay, String nameofCalss){
		
		//declaration for ArrayList<Exam>
		ArrayList<Exam> arrayList = new ArrayList<Exam>();
		//JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
         
        try (FileReader reader = new FileReader(".\\src\\main\\resources\\"+nameofCalss+".json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
 
            JSONArray examList = (JSONArray) obj;
            
            //test if file empty or not
             if(examList.isEmpty())
            	 return arrayList;
            //Iterate over Exam array
            for(Object ex : examList) {
            	//get Exam 
            	JSONObject examObject = (JSONObject)ex;
            	examObject = (JSONObject)examObject.get(nameofCalss);
            	//test
            	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            	String sDate= sdf.format(dateDay);
            	
            	try {
					Date date1=new SimpleDateFormat("dd/MM/yyyy").parse((String)examObject.get("date"));
					String sDate2 = sdf.format(date1);
					
	            	if(sDate2.equals(sDate))
            			arrayList.add(castJOtoExam(examObject, nameofCalss));
	            	
				} catch (java.text.ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

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
	 * without parameters, returns all ExamDriving or ExamCode
	 * @param nameofCalss
	 * @return
	 */
	public static ArrayList<Exam> getAll( String nameofCalss){
		
		//declaration for ArrayList<Exam>
		ArrayList<Exam> arrayList = new ArrayList<Exam>();
		//JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
         
        try (FileReader reader = new FileReader(".\\src\\main\\resources\\"+nameofCalss+".json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
 
            JSONArray examList = (JSONArray) obj;
            
            //test if file empty or not
             if(examList.isEmpty())
            	 return arrayList;
            //Iterate over Exam array
            for(Object ex : examList) {
            	//get Exam
            	JSONObject examObject = (JSONObject)ex;
            	examObject = (JSONObject)examObject.get(nameofCalss);
            	
            	arrayList.add(castJOtoExam(examObject, nameofCalss));

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
	
}
