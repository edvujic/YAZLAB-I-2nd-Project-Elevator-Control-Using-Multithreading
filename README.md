# YAZLABI-2nd-Project-Elevator-Control-Using-Multithreading

The goal of the project:
To control the influx of people in a mall using multithreading.

Shoping Mall Features
1. The number of floors in the shopping mall is 5.
2. There are 5 elevators in total.
3. One of the elevators works continuously. The rest, according to the influx of customers is active or idle.
4. The maximum capacity of the elevators is 10.
5. The transition between floors in elevators is 200 ms.



Features of the project components:
1) <b>Mall Login Thread</b>: Randomly between [1-10] with 500 ms time intervals
allows a large number of customers to enter the shopping mall (Ground Floor). Customers enter into the elevator queue to go to a random floor (1-4).
2) <b>Mall Exit Thread</b>: Randomly between [1-5] with 1000 ms time intervals
It enables a number of customers to exit the shopping mall (Ground Floor).
Customers exit from a random floor (1-4) to the elevator queue to go to the ground floor.
gets.
3) <b>Elevator Thread</b>: Used to transfer customers.
4) <b>Control Thread</b>: Controls the queues on floors. 
In case the total number of customers exceeds 2 times the capacity of the elevator (20), new elevator will be activated.
In case the total number of people waiting in the queue is below the elevator capacity, the active elevators turn off.


<a href="https://ibb.co/PccpwMp"><img src="https://i.ibb.co/c665Dw5/ss2.jpg" alt="ss2" border="0"></a>
