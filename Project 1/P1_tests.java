package Project_1;
// stringValue test
System.out.println(stringValue("Mason"));
System.out.println(stringValue("Ma son"));
System.out.println(stringValue("mass"));
System.out.println(stringValue("50 Cent"));
System.out.println(stringValue("aa"));
System.out.println(stringValue("aaa"));
System.out.println(stringValue("aaaa"));
System.out.println(stringValue("aaaaa"));

// expValue test
System.out.println(expValue(1, 0.000000000001));

// mirroredNum test
System.out.println(mirrorNum(4));
System.out.println(mirrorNum(98014));
System.out.println(mirrorNum(980014));
System.out.println(mirrorNum(9800014));
System.out.println(mirrorNum(-5931));
System.out.println(mirrorNum(12300));

// smallestSubArray test
int[][] duplicate1 = {{1,2,3,4,5},{6,7,8,9}};
System.out.println(removeDuplicates(duplicate1));
int[][] duplicate2 = {{1,2,2,2,3,4,5,5},{6,7,8,8,8,9}};
System.out.println(removeDuplicates(duplicate2));
int[][] duplicate3 = {{1,2,2,2,3,4,5,5},{5,5,5,5},{5,5,9}};
System.out.println(removeDuplicates(duplicate3));

// replaceElement test
int[][] array1 = {{1,2,3,4,5},{6,7,8}};
int[] replace1 = {0};
replaceElement(array1, 2, replace1);
for (int i = 0; i < array1.length; i++) {
	for (int j = 0; j < array1[i].length; j++)
		System.out.print(array1[i][j] + " ");
		System.out.println();
}
System.out.println();
int[][] array2 = {{1,2,3,4,5},{5,4,3,2}};
int[] replace2 = {-5,5};
replaceElement(array2, 5, replace2);
for (int i = 0; i < array2.length; i++) {
	for (int j = 0; j < array2[i].length; j++)
		System.out.print(array2[i][j] + " ");
		System.out.println();
}
System.out.println();
int[][] array3 =  {{0,1,2,3,1,2,3,5},{5,1,2,3,3,1,2,3}};
int[] replace3 = {1,2,3};
replaceElement(array3, 4, replace3);
for (int i = 0; i < array3.length; i++) {
	for (int j = 0; j < array3[i].length; j++)
		System.out.print(array3[i][j] + " ");
		System.out.println();
}
System.out.println();

// removeDuplicates test
int[][] duplicate1 = {{1,2,3,4,5},{6,7,8,9}};
		int[][] new1 = removeDuplicates(duplicate1);
		for (int i = 0; i < new1.length; i++) {
			for (int j = 0; j < new1[i].length; j++) {
				System.out.print(new1[i][j]);
			}
			System.out.println();
		}
		System.out.println();
		int[][] duplicate2 = {{1,2,2,2,3,4,5,5},{6,7,8,8,8,9}};
		int[][] new2 = removeDuplicates(duplicate2);
		for (int i = 0; i < new2.length; i++) {
			for (int j = 0; j < new2[i].length; j++) {
				System.out.print(new2[i][j]);
			}
			System.out.println();
		}
		System.out.println();
		int[][] duplicate3 = {{1,2,2,2,3,4,5,5},{5,5,5,5},{5,5,9}};
		int[][] new3 = removeDuplicates(duplicate3);
		for (int i = 0; i < new3.length; i++) {
			for (int j = 0; j < new3[i].length; j++) {
				System.out.print(new3[i][j]);
			}
			System.out.println();
		}

//vortex test
int[][] vortex1 = {{1,2,3},{4,5,6},{7,8,9}};
System.out.println(vortex(vortex1));
int[][] vortex2 = {{1,2},{3,4},{5,6},{7,8},{9,10}};
System.out.println(vortex(vortex2));
int[][] vortex3 = {{1,2,3,4,5},{6,7,8,9,10},{11,12,13,14,15},{16,17,18,19,20}};
System.out.println(vortex(vortex3));