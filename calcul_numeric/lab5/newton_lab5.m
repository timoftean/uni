function [N, dX] = newton_lab5(x,f,df,X)
    z = repelem(x,2);
    A = divdiff_lab5(x,f,df);
    
    for i = 1:length(X)
       N(i) = A(1,:) * cumprod([1, X(i) - z(1:end-1)])';  
    end

    dX = zeros(1,length(X));
    
    for i = 1:length(X)
        for k = 1:length(z) - 1
            dX(i) = dX(i) + polyval(polyder(poly(z(1:k))),X(i)) * A(1,k+1);
        end
    end
    
end
