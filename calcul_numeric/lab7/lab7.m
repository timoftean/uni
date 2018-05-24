a = 0;
b = 1;
f = @ (x) 2./(1+x.^2);
formTrapez = (b-a)/2*(f(a) + f(b))
formSimpson = (b-a)/6*(f(a)+4*f((a+b)/2)+f(b))


x = [0,0,1,1,0];
y = [0,f(0),f(1),0,0];

plot(x,y,'r');
hold on
x=0:0.01:1
plot(x,f(x));
