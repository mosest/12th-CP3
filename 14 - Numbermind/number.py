from decimal import *
NUM_LEN=0

getcontext().prec=60;

def mk_sol_mat(size):
	a = []
	for i in range(size):
		b = []
		for j in range(10):
			b.append(Decimal(1.0))
		a.append(b);
	return a;



def print_sol(sol_mat):
	solution = [];
	for i in range(NUM_LEN):
		cbv = Decimal(0.0);
		best=0;
		for j in range(10):
			if sol_mat[i][j] > cbv:
				cbv = Decimal(sol_mat[i][j]);
				best = j;
		solution.append(best);
	print solution;

def main():
	global NUM_LEN;

	input_file = open("input.txt","r")

	for line in input_file:
		nums = line.split(" ");
		test = nums[0];
		result = int(nums[1]);
		if NUM_LEN==0:
			NUM_LEN = len(test);
			print "LENGTH: ",NUM_LEN
			sol_mat = mk_sol_mat(NUM_LEN)

		for i in range(NUM_LEN):
			n = int(test[i]);
			sol_mat[i][n] = Decimal(Decimal(sol_mat[i][n])*Decimal(result)*Decimal(1.0)/Decimal(NUM_LEN));
			#print Decimal(sol_mat[i][n]),Decimal(result),Decimal(1.0),Decimal(NUM_LEN);
	print_sol(sol_mat);
	print sol_mat;
main();
	
		

