function z  = D(x,f)
   
z = zeros(length(x));
z(:,1) =f;
 
for k= 2:length(x)
  for i = 1:length(x)-k+1
    z(i,k) = (z(i+1,k-1) - z(i,k-1)) / (x(i+k-1) - x(i));
  end 
end

z=z(1,2:end);