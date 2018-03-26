% a)

t1= @(x) cos(acos(x));
t2= @(x) cos(2*acos(x));
t3= @(x) cos(3*acos(x));

x=-1:0.01:1;
r1=t1(x);
r2=t2(x);
r3=t3(x);

plot(x,r1,x,r2,x,r3)
hold off
% b)
x=-1:0.01:1

t=@ (x,n) cos(n*acos(x));
n=4;
r0=t(x,0);
r1=t(x,1);
plot(x,r0,x,r1);
hold on
for i = 1:n
    r2= 2*x.*r1 - r0;
    r0=r1
    r1=r2
    plot(x,r2)
end

