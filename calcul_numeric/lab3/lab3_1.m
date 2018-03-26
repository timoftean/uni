function y=lab3_1(x,f,xx)
a=ones(size(x));
n=length(x);
for i=1:n
z=x([1:i-1,i+1:n]); %toate elementele fara i
a(i)=1/ prod(x(i)-z); 
end
sum1=0;
sum2=0;
for i=1:n
sum1=sum1+(a(i)*f(i))/(xx-x(i));
sum2=sum2+a(i)/(xx-x(i));
end
y=sum1 / sum2;
end