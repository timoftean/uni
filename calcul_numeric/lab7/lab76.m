f = @(x) 2 ./ sqrt(pi) * exp(-1 * x .^ 2);

a = 0;
b = 0.5;

ret = rtf(a, b, 4, f)
ret = rtf(a, b, 10, f)

