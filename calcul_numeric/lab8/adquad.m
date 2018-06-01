function r = adquad(a,b,er,f)
    I1 = simp(a,b,f);
    I2 = simp(a,(a+b)/2, f) + simp((a+b)/2, b, f);
    if(abs(I1-I2) < 15*er)
        r = I2;
        return
    else
        r = adquad(a,(a+b)/2,er/2,f) + adquad((a+b)/2, b, er/2,f);    
    end
end