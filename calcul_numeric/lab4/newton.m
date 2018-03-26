function rez = newton(X,x,f)
 
z=D(x,f);
suma = 0;
 z
for i= 1:length(x)-1
  p=1;
  for j=1:i
    p = p * (X-x(j));
  end
  p=p*z(i);
  suma = suma + p;
end
suma = f(1)+suma;
suma
rez = suma;