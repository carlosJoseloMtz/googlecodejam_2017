


def main():
    # TODO read the file
    # TODO write the file
    solve_pancakes('+--+ 3')

def solve_pancakes(hc):
    case = hc.split(' ')
    line = case[0]
    flipper = case[1]
    print line, flipper

def call_me(line, indx, flipper, prev_path, go_backwards=False):
    end = indx + flipper
    # calculate the new index based on the current navigation
    nindx = indx - 1 if go_backwards else indx + 1
    print 'iteration', line, 'going backwards?', go_backwards, 'indx', indx
    got_to_end = end > len(line) or indx < 0 and go_backwards
    if got_to_end and go_backwards:
        return line
    # if we find a movement that was previously -successfully- executed, just skip that one
    if line[indx:end] in prev_path and not are_happy_sides_up(line[indx:end]):
        return call_me(line, nindx, flipper, prev_path, go_backwards)
    if got_to_end and not go_backwards:
        return call_me(line, len(line) - flipper, flipper, prev_path, True)
    # easy way out
    if are_happy_sides_up(line):
        print 'returning new line', line
        return line
    psegment = line[indx:end]
    suitable = find_suitable_flipper_blank_sides(line, flipper)
    if suitable[1] == True:
        print 'saving', psegment
        prev_path.append(psegment)
        line = suitable[0]
        return call_me(line, indx, flipper, prev_path, go_backwards)
    # when we find an specific segment that could be flipped
#    if is_blank_side_up(line[indx:end]):
    line = flip_hc(line, indx, flipper)
    try:
        return call_me(line, nindx, flipper, prev_path, go_backwards)
    except RuntimeError as ex:
        print 'upsi :)', ex, line
        return line

def are_happy_sides_up(line):
    return line.count('+') == len(line)

def find_suitable_flipper_blank_sides(line, flipper):
    llen = len(line)
    for indx in range(llen):
        end = indx + flipper
        # verify we do not overflow the line, we keep the search inside the line :)
        if end > llen:
            break
        # if the segment / fragment of the line is all blank side up
        if is_blank_side_up(line[indx:end]):
            return [flip_hc(line, indx, flipper), True]
    return [line, False]

def is_blank_side_up(segment):
    return segment.count('-') == len(segment)

def flip_hc(line, start, flipper):
    '''
    Flips the pancake line based on the start position and the flipper size.
    '''
    end = start + flipper
    return line[:start] + line[start:end].replace('-', 'o').replace('+', '-').replace('o', '+') + line[end:]
#    return line

# flip_hc('+--+', 1, 2)

# find_suitable_flipper_blank_sides('---+-++-', 3)

call_me('---+++--', 0, 3, [])

# main()
