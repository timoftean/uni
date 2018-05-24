x = [1, 2, 3, 4, 5, 6, 7];
fx = [13,15,20,14,15,13,10];

m = length(x);

a = (m* sum(x .* fx) - sum(x)*sum(fx))/(m*sum(x.^2) - (sum(x)^2))
b = (sum(x.^2)*sum(fx) - sum(x.*fx)* sum(x))/(m*sum(x.^2)-(sum(x)^2))

temperature = a*8+b

minimum  = sum((fx -(a * x + b)).^2)

hold on
plot(x,fx,'*')
plot(x,a.*x+b)