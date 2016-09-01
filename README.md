# FertilizerManagementSystem
Management system for efficient utilization of fertilizers by farmers 

##INTRODUCTION
This system is created to provide an efficient and portable way to help the farmers understand about their fields and its requirement.
###CURRENT PROBLEM:
At present the official need to go to each and every farm and collect the soil samples of the fields. They then need to bring it back to the laboratory for analysing the nutrient contents in the soil. After the assessment , he notify the farmer about the requirement of the soil for a particular crop . The farmer goes to the fertilizer shop and make a purchase of the fertilizer. The current time frame for complete execution of this method is almost 1 month.
###SOLUTION
To solve this problem , we have created a management system with a hardware which will assuage this task from months to days. With the help of our hardware and software solution , the task will be done in few minutes and the fertilizer would be sent to the farmer in few days. We are using a NPK sensor to sense the nutrient contents of the soil. The nutrient contents are converted into electrical signals  using sensor which are then fed to arduino controller . The arduino samples the analog data into digital data using ADC pins. The value is then passed on to a java program (uploaded) . The program computes the most suitable crop which can be grown on the field and report any deficieny if any . This task is provided by comparing the value from the database . The database stores the fertilizer requirements of all the crops and their nutrient requirements . The farmer can also specify which crop he wants in his field and then the program can tell you about the requirement for that crop. If the farmer approves the required amount of fertilizer(because money is involved here ;) ) , the message is then sent to remote shop using the GSM module in the form of text message with all the details of the delivery.

###Hardware requirements:
1. Arduino UNO board
2. GSM module
3. NPK sensor

###Software requirement:
Oracle database for storing the data.
Java Runtime environment
Arduino IDE (for programming arduino board)

