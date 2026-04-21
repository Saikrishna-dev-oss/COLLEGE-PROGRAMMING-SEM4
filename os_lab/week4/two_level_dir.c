#include <stdio.h>
#include <string.h>
#include <stdlib.h>

struct Directory
{
    char dname[10];
    char fname[10][10];
    int fcnt;
} dir[10];

int dcnt = 0;

void createDir()
{
    printf("Enter directory name: ");
    scanf("%s", dir[dcnt].dname);
    dir[dcnt].fcnt = 0;
    dcnt++;
    printf("Directory created\n");
}

void createFile()
{
    char d[10];
    printf("Enter directory name: ");
    scanf("%s", d);
    for (int i = 0; i < dcnt; i++)
    {
        if (!strcmp(d, dir[i].dname))
        {
            printf("Enter file name: ");
            scanf("%s", dir[i].fname[dir[i].fcnt++]);
            printf("File created\n");
            return;
        }
    }
    printf("Directory not found\n");
}

void deleteFile()
{
    char d[10], f[10];
    printf("Enter directory name: ");
    scanf("%s", d);
    for (int i = 0; i < dcnt; i++)
    {
        if (!strcmp(d, dir[i].dname))
        {
            printf("Enter file name: ");
            scanf("%s", f);
            for (int k = 0; k < dir[i].fcnt; k++)
            {
                if (!strcmp(f, dir[i].fname[k]))
                {
                    strcpy(dir[i].fname[k], dir[i].fname[--dir[i].fcnt]);
                    printf("File deleted\n");
                    return;
                }
            }
            printf("File not found\n");
            return;
        }
    }
    printf("Directory not found\n");
}

void searchFile()
{
    char d[10], f[10];
    printf("Enter directory name: ");
    scanf("%s", d);
    for (int i = 0; i < dcnt; i++)
    {
        if (!strcmp(d, dir[i].dname))
        {
            printf("Enter file name: ");
            scanf("%s", f);
            for (int k = 0; k < dir[i].fcnt; k++)
            {
                if (!strcmp(f, dir[i].fname[k]))
                {
                    printf("File found\n");
                    return;
                }
            }
            printf("File not found\n");
            return;
        }
    }
    printf("Directory not found\n");
}

void display()
{
    if (!dcnt)
    {
        printf("No directories\n");
        return;
    }
    printf("Directory\tFiles\n");
    for (int i = 0; i < dcnt; i++)
    {
        printf("%s\t", dir[i].dname);
        for (int k = 0; k < dir[i].fcnt; k++)
            printf("%s ", dir[i].fname[k]);
        printf("\n");
    }
}

int main()
{
    int ch;
    while (1)
    {
        printf("\n\n 1. Create Directory\t 2. Create File\t 3. Delete File");
        printf("\n 4. Search File \t 5. Display \t 6. Exit");
        printf("\n Enter your choice -- ");
        scanf("%d", &ch);
        switch (ch)
        {
        case 1:
            createDir();
            break;
        case 2:
            createFile();
            break;
        case 3:
            deleteFile();
            break;
        case 4:
            searchFile();
            break;
        case 5:
            display();
            break;
        case 6:
            exit(0);
        }
    }
}

// #include <stdio.h>
// #include <string.h>
// #include <stdlib.h>

// // Structure to represent a directory
// // Each directory has a name, an array of file names, and a file count
// struct {
//     char dname[10];       // Directory name
//     char fname[10][10];   // File names inside the directory
//     int fcnt;             // Number of files in the directory
// } dir[10];                // Array of directories (max 10)

// void main() {
//     int i, ch, dcnt, k;   // Counters and choice variable   k is used to search files
//     char f[30], d[30];    // Buffers for file and directory names

//     dcnt = 0; // Number of directories created so far

//     while (1) {
//         // Menu options
//         printf("\n\n 1. Create Directory\t 2. Create File\t 3. Delete File");
//         printf("\n 4. Search File \t 5. Display \t 6. Exit");
//         printf("\n Enter your choice -- ");
//         scanf("%d", &ch);

//         switch (ch) {
//             case 1: // Create a new directory
//                 printf("\n Enter name of directory -- ");
//                 scanf("%s", dir[dcnt].dname);
//                 dir[dcnt].fcnt = 0; // Initially no files
//                 dcnt++;
//                 printf("Directory created");
//                 break;

//             case 2: // Create a file inside a directory
//                 printf("\n Enter name of the directory -- ");
//                 scanf("%s", d);

//                 for (i = 0; i < dcnt; i++) {
//                     if (strcmp(d, dir[i].dname) == 0) {
//                         printf("Enter name of the file -- ");
//                         scanf("%s", dir[i].fname[dir[i].fcnt]);
//                         dir[i].fcnt++;
//                         printf("File created");
//                         break;
//                     }
//                 }

//                 if (i == dcnt) // Directory not found
//                     printf("Directory %s not found", d);
//                 break;

//             case 3: // Delete a file from a directory
//                 printf("\n Enter name of the directory -- ");
//                 scanf("%s", d);

//                 for (i = 0; i < dcnt; i++) {
//                     if (strcmp(d, dir[i].dname) == 0) {
//                         printf("Enter name of the file -- ");
//                         scanf("%s", f);

//                         for (k = 0; k < dir[i].fcnt; k++) {
//                             if (strcmp(f, dir[i].fname[k]) == 0) {
//                                 printf("File %s is deleted ", f);
//                                 dir[i].fcnt--;
//                                 // Replace deleted file with last file in list
//                                 strcpy(dir[i].fname[k], dir[i].fname[dir[i].fcnt]);
//                                 goto jmp;
//                             }
//                         }

//                         printf("File %s not found", f);
//                         goto jmp;
//                     }
//                 }

//                 printf("Directory %s not found", d);
//             jmp:
//                 break;

//             case 4: // Search for a file in a directory
//                 printf("\n Enter name of the directory -- ");
//                 scanf("%s", d);

//                 for (i = 0; i < dcnt; i++) {
//                     if (strcmp(d, dir[i].dname) == 0) {
//                         printf("Enter the name of the file -- ");
//                         scanf("%s", f);

//                         for (k = 0; k < dir[i].fcnt; k++) {
//                             if (strcmp(f, dir[i].fname[k]) == 0) {
//                                 printf("File %s is found ", f);
//                                 goto jmp1;
//                             }
//                         }

//                         printf("File %s not found", f);
//                         goto jmp1;
//                     }
//                 }

//                 printf("Directory %s not found", d);
//             jmp1:
//                 break;

//             case 5: // Display all directories and their files
//                 if (dcnt == 0)
//                     printf("\n No Directories ");
//                 else {
//                     printf("\nDirectory\tFiles");
//                     for (i = 0; i < dcnt; i++) {
//                         printf("\n%s\t\t", dir[i].dname);
//                         for (k = 0; k < dir[i].fcnt; k++)
//                             printf("\t%s", dir[i].fname[k]);
//                     }
//                 }
//                 break;

//             default: // Exit program
//                 exit(0);
//         }
//     }
// }
