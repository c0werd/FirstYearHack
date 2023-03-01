import java.util.*;  
class Challenge2BotComparator implements Comparator{  
public int compare(Object o1,Object o2){  
Challenge2Bot b1=(Challenge2Bot)o1;  
Challenge2Bot b2=(Challenge2Bot)o2;  
  
if(b1.getInspections()==b2.getInspections())  
return 0;  
else if(b1.getInspections()>b2.getInspections())  
return 1;  
else  
return -1;  
}  
}  