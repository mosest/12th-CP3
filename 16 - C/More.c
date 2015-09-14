//Tara Moses
//More C Programming I Guess
//May 16, 2014

#include <stdio.h>
#include <string.h>

typedef struct Ball {
	int radius;
	char color[80];
	char name[80];
	int isPopped;
	int psi;
} ballTD;

void pop(ballTD *ball) {
	if (ball->isPopped==0) {
		ball->isPopped=1;
		ball->psi=0;
		printf("Ball says: 'I THOUGHT YOU LOVED MEEEeee...'");
	} else printf("Ball says: '...'", ball->name);
};

void bounce(ballTD *ball) {
	if (ball->psi>0) {
		printf("Ball says: 'Whee!'", ball->name);
		ball->psi-=5;
	} else printf("Ball says: '... ow... i don't have any air left.'");
};

void paint(ballTD *ball, char c[80]) {
	strcpy(ball->color, c);
	printf("Ball says: 'Wow I sure do love this color! %s is my favorite.'", ball->color);
};

void patch(ballTD *ball) {
	if (ball->isPopped==0) printf("Ball says: 'Wow, a patch just for me? Thanks but I don't really need it... :3'");
	else {
		printf("Ball says: 'LIFE I LIVE AGAIN YES'");
		ball->isPopped=0;
	}
};

void inflate(ballTD *ball) {
	if (ball->psi<=50) {
		if (ball->isPopped==0) ball->psi+=5;
		else printf("Ball says: 'There's a hole in my side :('");
	} else {
		printf("Ball says: 'I'm getting too full... AAAAAH YOU POPPED ME WHYYYYWOULDYOUDOTHAAAATAAAAAAH'");
		ball->psi=0;
		ball->isPopped=1;
	}
};

void giveAway(ballTD *ball) {
	if (ball->psi>0 && ball->isPopped==0) printf("Ball says: 'Hooray a new home! I'll miss you lots :3'");
	else printf("Ball says: 'FREEDOM'");
};	

int main(void) {
	//variables yeah
	int radius;
	int choice;
	char color[80];
	char name[80];
	strcpy(name, "Charlie");
	strcpy(color,"blue");
	
	printf("LET'S WORK WITH A BALL.\n");
	
	printf("Radius (cm): ");
	scanf("%d", &radius);
	printf("Color (ROYGBIV): ");
	scanf("%s", color);
	printf("Wanna give it a name? ");
	scanf("%s", name);
	
	ballTD bouncyBall = {radius, color, name, 0, 50};
	ballTD *ballPointer = &bouncyBall;
	
	printf("\n%s says: 'Hi, I am a %s ball that is %d centimeters wide and I love you <3'\n", name, color, radius);
	
	printf("1) Pop it\n");
	printf("2) Bounce it\n");
	printf("3) Paint it\n");
	printf("4) Patch it so it's ready for more of your torture\n");
	printf("5) Inflate it\n");
	printf("6) Give it away to someone who might actually love it (exit)\n\n");
	
	printf("What would you like to do with %s? ", name);
	scanf("%d", &choice);
	
	while (choice!=6) {
		if (choice==1) {
			pop(ballPointer);
		} else if (choice==2) {
			bounce(ballPointer);
		} else if (choice==3) {
			printf("What color? ");
			scanf("%s", color);
			paint(ballPointer, color);
		} else if (choice==4) {
			patch(ballPointer);
		} else if (choice==5) {
			inflate(ballPointer);
		}
		
		printf("\n\nWhat next? ");
		scanf("%d", &choice);
	}
	
	giveAway(ballPointer);
	getch();		
}





















