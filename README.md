# COP4520A3

## Problem 1

What went wrong was that the servants may not have been removing from the gift chain as frequently as they should. To fix this, when the gift bag is empty, they can start to only write thank you notes.

Implemented the wait-free linked list from The Art of Multiprocessor Programming.

To run:

    make p1

## Problem 2

The solution implemented trades runtime efficiency for correctness and progress. This solution creates eight threads which will run for 60 iterations. Each iteration represents a minute. Before iterating, each sensor thread will create a TempData object which stores the the maximum temperature difference for 10 second interval, the start and end times for the interval, and an array to store the readings for the interval. In each iteration, the sensor will generate a random number for a reading and add it to a shared concurrent skiplist set provided in the Java concurrency library. Also, the tempdata object for that hour will be stored to a shared skiplist of tempdata objects. In the main thread, a report will be generated that lists the top 5 highest and lowest recorded temperatures and the largest temperature difference in a 10 minute interval. The main thread will repeat this process 24 times to represent 24 hours.

In terms of correctness, the solution accurately collects and reports on the recorded temperature.

In terms of a progress guarantee, the threads all access the ConcurrentSkipListSet provided by the Java Utils library. The solution is wait-free since there are no locks and every thread is guaranteed to complete their task of generating a temperature reading and store it in the shared skiplist which is wait-free.

In terms of overall efficiency, there are some improvements that could be made. Each sensor will run for 60 iterations while the main thread waits, then generates a report, and then the cycle repeats 24 times. This is slow since the sensor threads essentially are remade on each iteration so while a report is generated, more temperature data could be recorded. Furthermore, using a skiplist is inefficient in terms of space since a simpler concurrent data structure could be used as the only operations used are adding to the end of the list and removing from the front and end of the list. The benefit of the skiplist is its fast lookup times which are not utilized since there are no searches being done on the list. A heap may be a more appropriate data structure for this situation.

To run:

    make p2
