A = [401, -201; -800, 401];
b = [200; -200];

if cond(A)>1000
    fprintf('Bad condition system %f\n', cond(A))
else
    fprintf('Good condition system%f\n', cond(A))
end