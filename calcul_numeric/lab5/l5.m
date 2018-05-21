x = [0 3 5 8 13];
y = [0 225 383 623 993];
df = [75 77 80 74 72];


[A, B] = newton_lab5(x,y,df,10);
A
B

X=-5:0.01:5;

[A, B] = newton_lab5(x,y,df,X);
plot(A,B)