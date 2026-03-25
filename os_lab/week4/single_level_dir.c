// #include<stdio.h> 
// // #include<conio.h>
// #include<string.h> 

// int main() {

//     int nf=0,i=0,j=0,ch;

//     char mdname[10],fname[10][10],name[10]; 
//     // clrscr();
//     printf("Enter the directory name:"); 
//     scanf("%s",mdname);

//     printf("Enter the number of files:"); 
//     scanf("%d",&nf);

//     do {
//         printf("Enter file name to be created:"); 
//         scanf("%s",name);
        
//         for(i=0; i<nf; i++) {
//             if(!strcmp(name,fname[i])) 
//             break;
//         }
//         if(i == nf) {
//             strcpy(fname[j++],name); 
//             nf++;
//         }

//         else
//             printf("There is already %s\n",name);

//         printf("Do you want to enter another file(yes - 1 or no - 0):"); 
//         scanf("%d",&ch);

//     } while(ch==1);

//     printf("Directory name is:%s\n",mdname); 
//     printf("Files names are:");

//     for(i=0;i<j;i++) 
//         printf("\n%s",fname[i]); 
//     // getch();

//     return 0;
// }



//Directory and file management in C
#include <stdio.h>
#include <string.h>

int main() {
    int nf = 0, i = 0, j = 0;
    char mdname[10], fname[10][10], name[10];

    printf("Enter the directory name: ");
    scanf("%s", mdname);

    printf("Enter the number of files: ");
    scanf("%d", &nf);

    for (j = 0; j < nf; ) {
        printf("Enter file name to be created: ");
        scanf("%s", name);

        // Check for duplicates
        for (i = 0; i < j; i++) {
            if (!strcmp(name, fname[i])) {
                break;
            }
        }

        if (i == j) { // not found, so add
            strcpy(fname[j], name);
            j++;
        } else {
            printf("There is already %s\n", name);
        }
    }

    printf("Directory name is: %s\n", mdname);
    printf("File names are:");
    for (i = 0; i < j; i++) {
        printf("\n%s", fname[i]);
    }

    return 0;
}
