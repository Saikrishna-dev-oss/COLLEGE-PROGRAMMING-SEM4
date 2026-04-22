#include<stdio.h>
#include<stdlib.h>
#include<string.h>

struct node
{
    char name[20];
    int type;           //1 = Directory , 2 = File
    int children;
    struct node *child[5];
};

void create(struct node *root, char parent[])
{
    int i;

    printf("\nEnter name of node under %s: ", parent);
    scanf("%s",root->name);

    printf("Enter type (1 = Directory, 2 = File): ");
    scanf("%d",&root->type);

    if(root->type==1)
    {
        printf("Enter number of children for directory %s: ",root->name);
        scanf("%d",&root->children);

        for(i=0;i<root->children;i++)
        {
            root->child[i]=(struct node*)malloc(sizeof(struct node));
            create(root->child[i], root->name);
        }
    }
    else
    {
        root->children=0;
    }
}

void display(struct node *root,int level)
{
    int i;

    for(i=0;i<level;i++)
        printf("   ");

    if(root->type==1)
        printf("[DIR ] %s\n",root->name);
    else
        printf("[FILE] %s\n",root->name);

    for(i=0;i<root->children;i++)
        display(root->child[i],level+1);
}

void main()
{
    struct node *root;

    root = (struct node*)malloc(sizeof(struct node));

    printf("Creating ROOT directory\n");

    create(root,"ROOT");

    printf("\n\n------ DIRECTORY STRUCTURE ------\n\n");

    display(root,0);
}