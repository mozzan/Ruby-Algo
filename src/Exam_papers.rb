require 'set'

# sort array by put repeat elements in front of the array
def arrangeArray(num, relation)
    list = []
    completeArrange = Array.new(num)
    index = 0 

    while(index < relation.length)
        while(list.length != 0)
            key = list.delete_at(0)
            completeArrange[key] = true
            
            i = index
            while(i < relation.length)
                if(relation[i] == key)
                    if(i % 2 == 1)
                        if(completeArrange[relation[i - 1]] == nil)
                            list.push(relation[i - 1])
                        end
                        relation[index],relation[i - 1] = relation[i - 1],relation[index]
                        relation[index + 1],relation[i] = relation[i],relation[index + 1]
                    else
                        if(completeArrange[relation[i + 1]] == nil)
                            list.push(relation[i + 1])
                        end
                        relation[index],relation[i] = relation[i],relation[index]
                        relation[index + 1],relation[i + 1] = relation[i + 1],relation[index + 1]
                    end
                    index += 2
                end
                i += 1
           end
        end
        if(index < relation.length)
            list.push(relation[index])
            list.push(relation[index + 1])
            index += 2
        end
    end
    return relation
end

def seperatePapers(num, relation)
    if(relation.length == 0)
        return
    end
    relation = arrangeArray(num, relation)

    set_a = Set.new
    set_b = Set.new

    (0..relation.length - 1).step(2).each do |i|
        if((set_a.include?(relation[i]) && set_a.include?(relation[i + 1])) || (set_b.include?(relation[i]) && set_b.include?(relation[i + 1])))
            puts "Wrong Question"
            return 
        elsif((set_a.include?(relation[i]) && set_b.include?(relation[i + 1])) || (set_b.include?(relation[i]) && set_a.include?(relation[i + 1])))
            next
        elsif(set_a.include?(relation[i]) && !set_b.include?(relation[i + 1]))
            set_b.add(relation[i + 1])
        elsif(set_b.include?(relation[i]) && !set_a.include?(relation[i + 1]))
            set_a.add(relation[i + 1])
        elsif(!set_a.include?(relation[i]) && set_b.include?(relation[i + 1]))
            set_a.add(relation[i])
        elsif(!set_b.include?(relation[i]) && set_a.include?(relation[i + 1]))
            set_b.add(relation[i])
        elsif((!set_a.include?(relation[i]) && !set_b.include?(relation[i+1])) && (!set_b.include?(relation[i]) && !set_a.include?(relation[i+1])))
            set_a.add(relation[i])
            set_b.add(relation[i + 1])
        end
    end

    for i in 0..num - 1
        if(!set_a.include?(i) && !set_b.include?(i))
            set_a.add(i)
        end
    end
    
    print "set a : "
    print set_a.inspect
    print " set b : "
    print set_b.inspect
    print "\n"
end


File.open("test/Exam_papers.test", "r") do |f|
    index = 0
    numOfPeople = 0
    f.each_line do |line|
        if(index % 2 == 0)
            numOfPeople = line.to_i
        else 
            seperatePapers(numOfPeople, line.split(" ").map { |s| s.to_i })
        end
        index += 1
    end
end
