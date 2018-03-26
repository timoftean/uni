%I
%1 
a = [1 2 3];
b = [4; 5; 6];
c = a * b;
disp(c);
d = [4 5 6];
e = a.*d;
f = a.^2;
g = a.^d;
v = 1 : 6;
w = 2 : 3 : 10 ;
y = 10 : 1 : 0;
exp(a);
exp(1); 
sqrt(a);
m = max(a);
[m, k] = max(a);
h = [-2-9 8];
k = abs(h);
mean(a);
geomean(a);
sum(a);
prod(a);

%2
a=[1 2 13; 4 5 6; 7 8 9];
b=[4 8 12; -1 0 5; 2 3 8];
[m,n] = size(a);
t = b';
c=a*b;
disp(c);

d=a.*b;
disp(d);

e=a.^2;
disp(e);

size(a);
length(a);
m=mean(a);
m1=mean(a,2);
g=geomean(a);
s=sum(a);
s1=sum(a,2);
p=prod(a);
p1=prod(a,2);
max(a);
min(a);
diag(a);
m > 2;
a > b;
inv(b);
det(b);

f=abs(b);
b=[16 15 24]';
x=a\b;
disp(triu(a));
disp(tril(a));
m=[2 3 5; 7 11 13; 17 19 23];

disp(m(2,1));
m(2,:);
m(2,1:2);
m(2,2:end);
m(2:3,2:3);

% a
disp(eye(8)); 
disp(eye(5, 7)); 
disp(zeros(5, 7)); 
disp(ones(7, 9));

% b
M = magic(4);
disp(sum(M)); 
disp(sum(M, 2)); 
disp(sum(diag(M)));
disp(sum(diag(fliplr(M))));