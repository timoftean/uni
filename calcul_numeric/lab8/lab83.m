f = @(x) 100./(x.^2) .* sin(10./x);
a=1;
b=3;
er=10^(-4);

%n=50
n = 50; 
x = linspace(1,3,n);
plot(x,f(x))

ad = adquad(a,b,er,f)
rep = repsimp(x,a,b,n,f)
