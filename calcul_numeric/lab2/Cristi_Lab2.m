%Ex 1

%x=0:0.01:1;

%l1 = [1 0];
%l2 = [3/2 -1/2 0];
%l3 = [5/2 0 -3/2 0];
%l4 = [35/8 0 -15/4 0 3/8];

%p1 = polyval(l1, x);
%p2 = polyval(l2, x);
%p3 = polyval(l3, x);
%p4 = polyval(l4, x);

%subplot(2, 2, 1)
%plot(x, p1)

%subplot(2,2,2)
%plot(x,p2)

%subplot(2,2,3)
%plot(x,p3)

%subplot(2,2,4)
%plot(x,p4)


%Ex 2 a)

%t =  -1:0.01:1;

%for i = 1:3
%    T = cos( i * acos(t));
%    Tp = polyval(T, t);
    
%    subplot(2,2,i);
%    plot(t, Tp)
%end

% b)
%n = 10;

%t=-1:0.01:1;
%T0 = ones(size(t));
%T1 = t;

%figure
%plot(t,T0, t,T1);
%hold on;

%for i = 2:n
%    T = 2*t.*T1 - T0;
%    plot(t,T);
    
%    T0 = T1;
%    T1 = T;
%end


% Ex 3

%x = -1:0.01:3;
%n = 100;
%p0 = ones(size(x));

%P =@(x,k)(x.^k)./factorial(k);

%figure
%plot(x, p0);
%hold on;

%for i=1:n
%    p0 = p0 + P(x,i);
%    plot(x, p0);    
%end


%Ex 4

%h=0.25;
%i=0:6;
%a=1 + h*i;
%f = @(x) sqrt(5*x.^2 + 1);

%y = f(a);
%n = length(y);

%T = zeros(n);
%T(:,1) = y;


%for i =2:n
    
%    T(1:n-i+1,i)= diff(T(1:n-i+2,i-1));
%end

%T


%Ex 5

i=0:4;
x = (2 + 2*i)';
f = [4 8 14 16];

n= length(f);
T = zeros(n);
T(:,1) = f;

for i=2:4
    T(1:n-i+1, i) = diff(T(1:n-i+2,i-1))./(x(i:n) - x(1:n-i+1));
end

T








