#include<stdio.h>
#include<string.h>
 
#define TRUE 1
#define FALSE 0
 
int main(int argc, char** argv)
{
    int test_case;
    int T;
     
    scanf("%d", &T);
     
    for(test_case = 1; test_case <= T; ++test_case){
        int row;
        int col;
        int is_xor = TRUE;
         
        scanf("%d %d", &row, &col);
        char test_row[row][col+1];
        char bools[col];
        char not_bools[col];
         
        for(int i=0; i<row; i++) {
            scanf("%s", test_row[i]);
        }
         
        //printf("test_row[0] = %s\n", test_row[0]);
        for(int i=0; i<col; i++) {
            bools[i] = test_row[0][i];
            not_bools[i] = bools[i] == '1' ? '0' : '1';
        }
 
        for(int i=1; i<row; i++) {
            //printf("test_row[%d] = %s\n", i, test_row[i]);
            is_xor = strncmp(test_row[i], bools,col) == 0 || strncmp(test_row[i], not_bools,col) == 0 ? TRUE : FALSE;
             
            if(!is_xor) {
                break;
            }
        }            
        printf("#%d %s\n", test_case, is_xor ? "yes" : "no");
    }
    return 0;
}
