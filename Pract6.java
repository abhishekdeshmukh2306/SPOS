
//FIFO
import java.io.*;

public class Fifo {
public static void main(String[] args) throws IOException { BufferedReader br = new BufferedReader(new
InputStreamReader(System.in));
int frames, pointer = 0, hit = 0, fault = 0, ref_len; int buffer[];
int reference[]; int mem_layout[][];
System.out.println("Please enter the number of Frames: "); frames = Integer.parseInt(br.readLine());
System.out.println("Please enter the length of the Reference string:");
ref_len = Integer.parseInt(br.readLine()); reference = new int[ref_len];
mem_layout = new int[ref_len][frames]; buffer = new int[frames];
for (int j = 0; j < frames; j++) buffer[j] = -1;
System.out.println("Please enter the reference string: "); for (int i = 0; i < ref_len; i++) {
reference[i] = Integer.parseInt(br.readLine());
}
System.out.println();
for (int i = 0; i < ref_len; i++) { int search = -1;
for (int j = 0; j < frames; j++) { if (buffer[j] == reference[i]) {
search = j; hit++; break;
}
}
if (search == -1) {
buffer[pointer] = reference[i]; fault++;
pointer++;
 
if (pointer == frames) pointer = 0;
}
for (int j = 0; j < frames; j++) mem_layout[i][j] = buffer[j];

}
for (int i = 0; i < frames; i++) { for (int j = 0; j < ref_len; j++)
System.out.printf("%3d ", mem_layout[j][i]); System.out.println();
}
System.out.println("The number of Hits: " + hit);
System.out.println("Hit Ratio: " + (float) ((float) hit / ref_len));
System.out.println("The number of Faults: " + fault);
}
}
 
//Least Recently Used

import java.util.*; class LruAlgo
{
int p[],n,fr[],m,fs[],index,k,l,flag1=0,flag2=0,pf=0,frsize=3,i,j; Scanner src=new Scanner(System.in);
void read()
{

System.out.println("Enter page table size"); n=src.nextInt();
p=new int[n];
System.out.println("Enter element in page table"); for(int i=0;i<n;i++)
p[i]=src.nextInt();

System.out.println("Enter page frame size"); m=src.nextInt();
fr=new int[m]; fs=new int[m];
 
}
void display()
{
System.out.println("\n"); for(i=0;i<m;i++)
{
if(fr[i]==-1) System.out.println("[ ]"); else
System.out.println("["+fr[i]+"]");
}
}
void lru()
{
for(i=0;i<m;i++)
{
fr[i]=-1;
}
for(j=0;j<n;j++)
{
flag1=0;flag2=0; for(i=0;i<m;i++)
{
if(fr[i]==p[j])
{
flag1=1; flag2=1; break;
}
}
if(flag1==0)
{
for(i=0;i<m;i++)
{
if(fr[i]==-1)
{
fr[i]=p[j]; flag2=1; break;
}
}
}
if(flag2==0)
{
for(i=0;i<3;i++) fs[i]=0;
 
for(k=j-1,l=1;l<=frsize-1;l++,k--)
{
for(i=0;i<3;i++)
{
if(fr[i]==p[k]) fs[i]=1;
}
}
for(i=0;i<3;i++)
{
if(fs[i]==0) index=i;
}
fr[index]=p[j]; pf++;
}
System.out.print("Page : "+p[j]); display();
}
System.out.println("\n no of page faults :"+pf);
}
public static void main(String args[])
{
LruAlgo a=new LruAlgo(); a.read();
a.lru();
a.display();
}
}








PS D:\engineering\TE\spos> javac LruAlgo.java PS D:\engineering\TE\spos> java LruAlgo Enter page table size
10
Enter element in page table 1
5
1
2
6
2
 
7
1
5
1
Enter page frame size 3
Page : 1

[1]
[ ]
[ ]
Page : 5

[1]
[5]
[ ]
Page : 1

[1]
[5]
[ ]
Page : 2

[1]
[5]
[2]
Page : 6

[1]
[6]
[2]
Page : 2

[1]
[6]
[2]
Page : 7

[7]
[6]
[2]
Page : 1

[7]
[1]
[2]
Page : 5
 
[7]
[1]
[5]
Page : 1

[7]
[1]
[5]

no of page faults :4


[7]
[1]
[5]


// Optimal Algorithm

import java.io.BufferedReader; import java.io.IOException;
import java.io.InputStreamReader; public class OptimalReplacement {


public static void main(String[] args) throws IOException
{
BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); int frames, pointer = 0, hit = 0, fault = 0,ref_len;
boolean isFull = false; int buffer[];
int reference[];
int mem_layout[][];

System.out.println("Please enter the number of Frames: "); frames = Integer.parseInt(br.readLine());

System.out.println("Please enter the length of the Reference string: "); ref_len = Integer.parseInt(br.readLine());

reference = new int[ref_len]; mem_layout = new int[ref_len][frames]; buffer = new int[frames];
for(int j = 0; j < frames; j++) buffer[j] = -1;

System.out.println("Please enter the reference string: "); for(int i = 0; i < ref_len; i++)
{
reference[i] = Integer.parseInt(br.readLine());
}
System.out.println();
for(int i = 0; i < ref_len; i++)
{
int search = -1;
for(int j = 0; j < frames; j++)
{
if(buffer[j] == reference[i])
{
search = j; hit++; break;
}
}
 
if(search == -1)
{
if(isFull)
{
int index[] = new int[frames];
boolean index_flag[] = new boolean[frames]; for(int j = i + 1; j < ref_len; j++)
{
for(int k = 0; k < frames; k++)
{
if((reference[j] == buffer[k]) && (index_flag[k] == false))
{
index[k] = j; index_flag[k] = true; break;
}
}
}
int max = index[0]; pointer = 0;
if(max == 0)
max = 200;
for(int j = 0; j < frames; j++)
{
if(index[j] == 0)
index[j] = 200; if(index[j] > max)
{
max = index[j]; pointer = j;
}
}
}
buffer[pointer] = reference[i]; fault++;
if(!isFull)
{
pointer++;
if(pointer == frames)
{
pointer = 0; isFull = true;
}
}
}
for(int j = 0; j < frames; j++) mem_layout[i][j] = buffer[j];
}
 
for(int i = 0; i < frames; i++)
{
for(int j = 0; j < ref_len; j++) System.out.printf("%3d ",mem_layout[j][i]);
System.out.println();
}

System.out.println("The number of Hits: " + hit); System.out.println("Hit Ratio: " + (float)((float)hit/ref_len)); System.out.println("The number of Faults: " + fault);
}

}

