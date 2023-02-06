# Best Time to Buy and Sell Stocks (Dynamic Programming and Greedy)

### 1. Problem Definition

We are given a array of price predictions for m stocks for n consecutive days. The price of stock
i for day j is A[i][j] for i = 1, . . . , m and j = 1, . . . , n. You are tasked with finding the maximum
possible profit by buying and selling stocks. The predicted price at any day will always be a
non-negative integer. You can hold only one share of one stock at a time. You are allowed to
buy a stock on the same day you sell another stock. More formally,
- **Problem1** Given a matrix A of m × n integers (non-negative) representing the predicted prices of m
stocks for n days, find a single transaction (buy and sell) that gives maximum profit.
- **Problem2** Given a matrix A of m × n integers (non-negative) representing the predicted prices of m
stocks for n days and an integer k (positive), find a sequence of at most k transactions
that gives maximum profit. [Hint :- Try to solve for k = 2 first and then expand that
solution.]
- **Problem3** Given a matrix A of m × n integers (non-negative) representing the predicted prices of m
stocks for n days and an integer c (positive), find the maximum profit with no restriction
on number of transactions. However, you cannot buy any stock for c days after selling any
stock. If you sell a stock at day i, you are not allowed to buy any stock until day i + c + 1

### 2. Algorithm Design Tasks

You are asked to design three different algorithms for each problem, with varying time complexity requirement in order to conduct an experimental comparative study.
- **Alg1** Design a Θ(m ∗ n<sup>2</sup>) time brute force algorithm for solving Problem1
- **Alg2** Design a Θ(m ∗ n) time greedy algorithm for solving Problem1
- **Alg3** Design a Θ(m ∗ n) time dynamic programming algorithm for solving Problem1
- **Alg4** Design a Θ(m ∗ n<sup>2k</sup>) time brute force algorithm for solving Problem2
- **Alg5** Design a Θ(m ∗ n<sup>2</sup> ∗ k) time dynamic programming algorithm for solving Problem2
- **Alg6** Design a Θ(m ∗ n ∗ k) time dynamic programming algorithm for solving Problem2
- **Alg7** Design a Θ(m ∗ 2<sup>n</sup>) time brute force algorithm for solving Problem3
- **Alg8** Design a Θ(m ∗ n<sup>2</sup>) time dynamic programming algorithm for solving Problem3
- **Alg9** Design a Θ(m ∗ n) time dynamic programming algorithm for solving Problem3

### 3. Programming Tasks:

Once you complete the algorithm design tasks, perform the following programming tasks:
- **Task1** Give an implementation of Alg1.
- **Task2** Give an implementation of Alg2.
- **Task3a** Give a recursive implementation of Alg3 using Memoization.
- **Task3b** Give an iterative BottomUp implementation of Alg3.
- **Task4** Give an implementation of Alg4.
- **Task5** Give an implementation of Alg5.
- **Task6a** Give a recursive implementation of Alg6 using Memoization.
- **Task6b** Give an iterative BottomUp implementation of Alg6.
- **Task7** Give an implementation of Alg7.
- **Task8** Give an implementation of Alg8.
- **Task9a** Give a recursive implementation of Alg9 using Memoization.
- **Task9b** Give an iterative BottomUp implementation of Alg9.

### Language/Input/Output Specifications

You may use C++, Java. Your program must compile/run on the Thunder CISE server using
gcc/g++ or standard JDK. You may access the server using SSH client on thunder.cise.ufl.edu.
You must write a makefile document that creates an executable named Stocks. The task is
passed by an argument, e.g., when Stocks 3b is called from the terminal, your program needs
to execute the implementation of Task3b. Through out this assignment assume that the indices
of the stocks are 1 . . . m and the indices of the days are 1 . . . n.

#### **Problem1:**
For convenience assume that 1 ≤ m < 100, 1 ≤ n < 105 and ∀i 0 ≤ A[i][j] < 104.
If multiple buy/sell transaction pairs yield the maximum profit, output any one of them.

**Input:** Your program will read input from standard input (stdin) in the following order:
- Line 1 consists of two integers m and n separated by a single space.
- Next m lines each consists of n integers (prices for n days) separated by a single space.

**Output:** Print the optimal transaction info to standard output (stdout)
- A single line with 3 integers (Stock, BuyDay, & SellDay indices) separated by a space.

#### **Problem2:**
For convenience assume that 1 ≤ k < 100, 1 ≤ m < 100, 1 ≤ n < 1000 and
∀ij 0 ≤ A[i][j] < 1000. If multiple sets of transactions yield the maximum profit, output any
one of them.

**Input:** Your program will read input from standard input (stdin) in the following order
- Line 1 consists one integer k.
- Line 2 consists of two integers m and n separated by one space character.
- Next m lines each with n integers (predicted prices) separated by a single space.

**Output:** Print a set of (at most k) transactions that yields the max profit in order of dates.
- Each line with 3 integers (Stock, BuyDay, & SellDay indices) separated by a single space.

#### **Problem3:**
For convenience assume that 1 ≤ c < 100, 1 ≤ m < 100, 1 ≤ n < 1000 and ∀i 0 ≤ A[i][j] <
1000. If there are multiple possible sets of transactions that yield the maximum profit, output
any one of them.

**Input:** Your program will read input from standard input (stdin) in the following order:
- Line 1 consists one integer c.
- Line 2 consists of two integers m and n separated by one space character.
- Next m lines each with n integers (predicted prices) separated by a single space.

**Output:** Print a set of transactions that yields the max profit in order of dates.
- Each line with 3 integers (Stock, BuyDay, & SellDay indices) separated by a single space.
