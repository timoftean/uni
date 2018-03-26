function f1=aitken(x,f,x1,p)
    
    n=length(x); %length of available data
    D=x1-x; %diff
    A(:,1)=f;
    for i=2:n
        for j=2:i
            A(i,j)=(D(j-1)*A(i,j-1)-D(i)*A(j-1,j-1))/(x(i)-x(j-1));
        end
    end
    
    
    f1=A(n,n);