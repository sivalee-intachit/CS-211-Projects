//import org.junit.Assert;

public class QueueingSimulation {

	public static void main(String[] arg) {

		{
			// Before the test
			QueueSystem.setClock(0);
			QueueSystem.setQueues(null);

			// Create the system objects for Test01
			Client client1 = new Client("John1", "Peter1", 1977, "MALE", 1, 50, null);
			String[] items1 = { "Q1" };
			InformationRequest r11 = new InformationRequest("IR1", 5, 5, items1);
			Request[] rList1 = { r11 };
			client1.setRequests(rList1);
			Queue q1 = new Queue("Noah", 2);
			QueueSystem qS = new QueueSystem(1, false, false);
			qS.setQueues(new Queue[1]);
			Client[] clientsInWorld = new Client[1];
			clientsInWorld[0] = client1;
			QueueSystem.setClientsWorld(clientsInWorld);
			qS.getQueues()[0] = q1;

			System.out.println();
			System.out.println("TEST 1");
			System.out.println(q1.toString());
			System.out.println(q1.toString(false));
			
			// Assert.assertEquals(0, qS.getClock());
			// Assert.assertEquals("[Queue:1][  ]-----[  ][  ]", q1.toString());
			// Assert.assertEquals("[Queue:1][  ]-----[  ][  ]", q1.toString(false));

		}

		{
			// Before the test
			QueueSystem.setClock(0);
			QueueSystem.setQueues(null);
			
			// Create the system objects for Test02
			Client client1 = new Client("John1", "Peter1", 1977, "MALE", 1, 50, null);
			String[] items1 = { "Q1" };
			InformationRequest r11 = new InformationRequest("IR1", 5, 5, items1);
			Request[] rList1 = { r11 };
			client1.setRequests(rList1);
			Queue q1 = new Queue("Noah", 2);
			QueueSystem qS = new QueueSystem(1, false, false);
			qS.setQueues(new Queue[1]);
			Client[] clientsInWorld = new Client[1];
			clientsInWorld[0] = client1;
			QueueSystem.setClientsWorld(clientsInWorld);
			qS.getQueues()[0] = q1;

			//Simulation
			qS.increaseTime();

			System.out.println();
			System.out.println("TEST 2");
			System.out.println(q1.toString());
			System.out.println(q1.toString(false));

			// Assert.assertEquals(1, qS.getClock());
			// Assert.assertEquals("[Queue:1][02]-----[  ][  ]", q1.toString());
			// Assert.assertEquals("[Queue:1][05]-----[  ][  ]", q1.toString(false));

		}

		{
			// Before the test
			QueueSystem.setClock(0);
			QueueSystem.setQueues(null);
			
			// Create the system objects for Test03
			Client client1 = new Client("John1", "Peter1", 1977, "MALE", 1, 50, null);
			String[] items1 = { "Q1" };
			InformationRequest r11 = new InformationRequest("IR1", 5, 5, items1);
			Request[] rList1 = { r11 };
			client1.setRequests(rList1);
			Queue q1 = new Queue("Noah", 2);
			QueueSystem qS = new QueueSystem(1, false, false);
			qS.setQueues(new Queue[1]);
			Client[] clientsInWorld = new Client[1];
			clientsInWorld[0] = client1;
			QueueSystem.setClientsWorld(clientsInWorld);
			qS.getQueues()[0] = q1;

			//Simulation
			qS.increaseTime();
			qS.increaseTime();

			System.out.println();
			System.out.println("TEST 3");
			System.out.println(q1.toString());
			System.out.println(q1.toString(false));

			// Assert.assertEquals(2, qS.getClock());
			// Assert.assertEquals("[Queue:1][03]-----[  ][  ]", q1.toString());
			// Assert.assertEquals("[Queue:1][04]-----[  ][  ]", q1.toString(false));
		}

		{
			// Before the test
			QueueSystem.setClock(0);
			QueueSystem.setQueues(null);
			
			// Create the system objects for Test04
			Client client1 = new Client("John1", "Peter1", 1977, "MALE", 1, 50, null);
			String[] items1 = { "Q1" };
			InformationRequest r11 = new InformationRequest("IR1", 5, 5, items1);
			Request[] rList1 = { r11 };
			client1.setRequests(rList1);
			Queue q1 = new Queue("Noah", 2);
			QueueSystem qS = new QueueSystem(1, false, false);
			qS.setQueues(new Queue[1]);
			Client[] clientsInWorld = new Client[1];
			clientsInWorld[0] = client1;
			QueueSystem.setClientsWorld(clientsInWorld);
			qS.getQueues()[0] = q1;

			//Simulation
			qS.increaseTime();
			qS.increaseTime();
			qS.increaseTime();

			System.out.println();
			System.out.println("TEST 4");
			System.out.println(q1.toString());
			System.out.println(q1.toString(false));

			// Assert.assertEquals(3, qS.getClock());
			// Assert.assertEquals("[Queue:1][04]-----[  ][  ]", q1.toString());
			// Assert.assertEquals("[Queue:1][03]-----[  ][  ]", q1.toString(false));
		}

		{
			/// Before the test
			QueueSystem.setClock(0);
			QueueSystem.setQueues(null);

			// Create the system objects for Test05
			Client client1 = new Client("John1", "Peter1", 1977, "MALE", 1, 50, null);
			String[] items1 = { "Q1" };
			InformationRequest r11 = new InformationRequest("IR1", 5, 5, items1);
			Request[] rList1 = { r11 };
			client1.setRequests(rList1);
			Queue q1 = new Queue("Noah", 2);
			QueueSystem qS = new QueueSystem(1, false, false);
			qS.setQueues(new Queue[1]);
			Client[] clientsInWorld = new Client[1];
			clientsInWorld[0] = client1;
			QueueSystem.setClientsWorld(clientsInWorld);
			qS.getQueues()[0] = q1;

			//Simulation
			qS.increaseTime();
			qS.increaseTime();
			qS.increaseTime();
			qS.increaseTime();

			System.out.println();
			System.out.println("TEST 5");
			System.out.println(q1.toString());
			System.out.println(q1.toString(false));

			// Assert.assertEquals(4, qS.getClock());
			// Assert.assertEquals("[Queue:1][05]-----[  ][  ]", q1.toString());
			// Assert.assertEquals("[Queue:1][02]-----[  ][  ]", q1.toString(false));
		}

		{
			// Before the test
			QueueSystem.setClock(0);
			QueueSystem.setQueues(null);
			
			// Create the system objects for Test06
			Client client1 = new Client("John1", "Peter1", 1977, "MALE", 1, 50, null);
			String[] items1 = { "Q1" };
			InformationRequest r11 = new InformationRequest("IR1", 5, 5, items1);
			Request[] rList1 = { r11 };
			client1.setRequests(rList1);
			Queue q1 = new Queue("Noah", 2);
			QueueSystem qS = new QueueSystem(1, false, false);
			qS.setQueues(new Queue[1]);
			Client[] clientsInWorld = new Client[1];
			clientsInWorld[0] = client1;
			QueueSystem.setClientsWorld(clientsInWorld);
			qS.getQueues()[0] = q1;

			//Simulation
			qS.increaseTime();
			qS.increaseTime();
			qS.increaseTime();
			qS.increaseTime();
			qS.increaseTime();

			System.out.println();
			System.out.println("TEST 6");
			System.out.println(q1.toString());
			System.out.println(q1.toString(false));

			// Assert.assertEquals(5, qS.getClock());
			// Assert.assertEquals("[Queue:1][06]-----[  ][  ]", q1.toString());
			// Assert.assertEquals("[Queue:1][01]-----[  ][  ]", q1.toString(false));
		}

		{
			// Before the test
			QueueSystem.setClock(0);
			QueueSystem.setQueues(null);

			// Create the system objects for Test07
			Client client1 = new Client("John1", "Peter1", 1977, "MALE", 1, 50, null);
			String[] items1 = { "Q1" };
			InformationRequest r11 = new InformationRequest("IR1", 5, 5, items1);
			Request[] rList1 = { r11 };
			client1.setRequests(rList1);
			Queue q1 = new Queue("Noah", 2);
			QueueSystem qS = new QueueSystem(1, false, false);
			qS.setQueues(new Queue[1]);
			Client[] clientsInWorld = new Client[1];
			clientsInWorld[0] = client1;
			QueueSystem.setClientsWorld(clientsInWorld);
			qS.getQueues()[0] = q1;

			//Simulation
			qS.increaseTime();
			qS.increaseTime();
			qS.increaseTime();
			qS.increaseTime();
			qS.increaseTime();
			qS.increaseTime();

			System.out.println();
			System.out.println("TEST 7");
			System.out.println(q1.toString());
			System.out.println(q1.toString(false));

			// Assert.assertEquals(6, qS.getClock());
			// Assert.assertEquals("[Queue:1][  ]-----[  ][  ]", q1.toString());
			// Assert.assertEquals("[Queue:1][  ]-----[  ][  ]", q1.toString(false));
		}
	}
}