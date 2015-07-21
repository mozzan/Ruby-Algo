def getMaxScore(targets)
    if(targets.length == 0)
        return -1
    end
    tempScore = Array.new(targets.length,0)
    lastSelectedIndex = 0
    pathStore = []

    for i in 0..targets.length - 1
        if(i == 0)
            tempScore[i] = targets[i]
            lastSelectedIndex = 0 
            pathStore[0] = [0]
        elsif(i == 1)
            if(targets[1] > targets[0])
                tempScore[1] = targets[1]
                lastSelectedIndex = 1 
                pathStore[1] = [1]
            else
                tempScore[1] = targets[0]
                pathStore[1] = [0]
            end
        else
            if(lastSelectedIndex == i - 2)
                tempScore[i] = tempScore[i - 2] + targets[i]
                lastSelectedIndex = i
                pathStore[i] = pathStore[i - 2].clone.push(i) 
            elsif(lastSelectedIndex == i - 1)
                if(tempScore[i - 2] + targets[i] > tempScore[i - 1])
                    tempScore[i] = tempScore[i - 2] + targets[i]
                    lastSelectedIndex = i
                    pathStore[i] = pathStore[i - 2].clone.push(i)
                else
                    tempScore[i] = tempScore[i - 1]
                    pathStore[i] = pathStore[i - 1].clone
                end
            end
        end
    end 
    print targets
    print "\nScore : "
    print tempScore[targets.length - 1]
    print ", Select : " 
    print pathStore[targets.length - 1] 
    print "\n"
end

File.open("test/Shooter.test", "r") do |f|
    f.each_line do |line|
        getMaxScore(line.split(" ").map { |s| s.to_i })
    end
end
