import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;


public class PuzzleSolve {

	public LinkedList uniqueList, assignmentList;
	public String puzzleString, answer, second, first;
	public int uniqueCount, numberNotSolutions;

	public PuzzleSolve()
	{
		getInput();
		uniqueList = new LinkedList();
		uniqueCount = findUniques(first, second, answer);
		uniqueList.printList("Unique Characters");
		
		LinkedList assignmentList = new LinkedList();			// will contain values assigned to letters in uniqueList
		LinkedList unusedNumbers = new LinkedList();
		
		for(int i = 0; i < 10; i++)								// populates unused number list with digits 0 - 9
		{
			unusedNumbers.addLast(new Node(String.valueOf(i), null));
		}
		recursiveSolve(uniqueCount, unusedNumbers, assignmentList);

	}
	
	public void evaluate(LinkedList assignmentList)
	{
		
		String fCopy = first;
		String sCopy = second;
		String aCopy = answer;
		
		String aTempChar = null;
		
		for(int i = 0; i < assignmentList.size; i++)
		{
			uniqueList.removeFirst();
			assignmentList.removeFirst();
			
			Node uNode = uniqueList.popped;
			Node aNode = assignmentList.popped;
			
			String tempChar = uNode.getElement();
			String tempNum = aNode.getElement();
			aTempChar = tempChar;
			
			// System.out.println(tempChar + " : " + tempNum);
			
			fCopy = fCopy.replaceAll(tempChar, tempNum);
			sCopy = sCopy.replaceAll(tempChar, tempNum);
			aCopy = aCopy.replaceAll(tempChar, tempNum);
			
			uniqueList.addLast(uNode);
			assignmentList.addLast(aNode);
			
		}
		
		int firstInt = Integer.parseInt(fCopy);				// converts strings of numbers to int
		int secondInt = Integer.parseInt(sCopy);
		int answerInt = Integer.parseInt(aCopy);
		int sum = firstInt + secondInt;
		if(sum == answerInt)
		{
			String f = Integer.toString(firstInt);
			String s = Integer.toString(secondInt);
			String a = Integer.toString(answerInt);
			
			System.out.println("Found solution: " + firstInt + " + " + secondInt + " = " + answerInt);
			
			for(int i = 0; i < f.length(); i++)
			{
				System.out.println((first.charAt(i)) + " : " + f.charAt(i) + " ");
			}
			for(int j = 0; j < s.length(); j++)
			{
				System.out.println((second.charAt(j)) + " : " + s.charAt(j) + " ");
			}
			for(int k = 0; k < s.length(); k++)
			{
				System.out.println((answer.charAt(k)) + " : " + a.charAt(k)+ " ");
			}
		}
	}
	public int findUniques(String a, String b, String c)
	{
		a = first;
		b = second;
		c = answer;
		
		char[] firstChars = a.toCharArray();
		char[] secondChars = b.toCharArray();
		char[] ansChars = c.toCharArray();
		
		Set<Character> charSet = new LinkedHashSet<Character>(); // HashSet automatically removes duplicates
		
		for(char fChar : firstChars)
		{
			charSet.add(fChar);
		}
		for(char sChar : secondChars)
		{
			charSet.add(sChar);
		}
		for(char aChar: ansChars)
		{
			charSet.add(aChar);
		}
		
		for(Character aChar : charSet)
		{
			uniqueList.addFirst(new Node(Character.toString(aChar), null));
		}
		return (int) uniqueList.size;
	}
	public void getInput()
	{
		LinkedList wordList = new LinkedList();
		System.out.println("Enter a puzzle to solve: ");
		Scanner myScan = new Scanner(System.in);
		myScan.useDelimiter("\\s+");
		while(myScan.hasNext()) 							// parse strings from input
		{
			String current = myScan.next();
			if(current!="+" && current!="=") 				// ignore math symbols
			{
				Node myNode = new Node(current, null);
				wordList.addFirst(myNode);
				// System.out.println(current + " successfully added.");
				if(wordList.size != 3) 						// workaround to break out of while loop
				{
					current = myScan.next();
				}
				else
				{
					break;
				}
				
			}
		}
		myScan.close();
		// System.out.println("Scanner closed");
		
		wordList.removeFirst();
		answer = wordList.popped.getElement();
		
		wordList.removeFirst();
		second = wordList.popped.getElement();
		
		wordList.removeFirst();
		first = wordList.popped.getElement();
		
		// System.out.println(first + " + " + second + " = " + answer);
	}
	
	public void recursiveSolve(int d, LinkedList unusedNumbers, LinkedList assignmentList)
	{
		
		if(d == 0)
		{
			evaluate(assignmentList);
		}
		else
		{
			for(int i = 0; i < unusedNumbers.size; i++)			// for each digit in unusedNumbers
			{
				unusedNumbers.removeFirst();					// remove head so next recursive call has one less digit to use
				assignmentList.addFirst(unusedNumbers.popped);
				// System.out.println(unusedNumbers.popped.getElement() + " added to asslist");
				recursiveSolve(d - 1, unusedNumbers, assignmentList);			
				assignmentList.removeFirst();
				// System.out.println(assignmentList.popped.getElement() + " removed from asslist");
				unusedNumbers.addLast(assignmentList.popped);
			}
		}
	}
	
	
	public static void main(String[] args) {
		
		
		new PuzzleSolve();
		

	}

}
