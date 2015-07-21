def getExpectedPrice(plates) 
    if(plates.length == 0)
        return -1
    end
    length = plates.length
    expected = Array.new(length, 0.0)

    expected[0] = plates[0].to_f
    for i in 1..(length-1)
       for j in (i).downto(1)
           tmp = 0.0
           for x in 0..(j-1)
               tmp += expected[x]
           end
           expected[j] = tmp + plates[j].to_f
       end 
       expected[0] = plates[0]
       for j in 0..i
           expected[j] = expected[j].to_f / (i + 1)
       end
    end
    total = 0.0
    expected.each { |x| total += x }
    return total
end

File.open("test/Tower_of_Hanoi.test", "r") do |f|
    f.each_line do |line|
        puts getExpectedPrice(line.split(" ").map { |s| s.to_i })
    end
end
