package algorithm.programmers;

import java.util.*;

public class Skill_Tree {

    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        Set<Character> filterSet = new LinkedHashSet<>();
        Map<String, Integer> treeOrder = new LinkedHashMap<>();
        String[] split = skill.split("");
        for (int k = 0; k < split.length; k++) {
            treeOrder.put(split[k], k + 1);
        }

        // 아래 같은 문자열인지 바꿔주는 메서드가 다음과 같이 정규표현식으로 치환할 수 있다.
        //	String stree = skill_trees[i].replaceAll("[^" + skill + "]", ""); // skill문자를 제외한 문자 치환

        String[] skillsOnly = new String[skill_trees.length];

        for (char c : skill.toCharArray()) {
            filterSet.add(c);
        }

        for (int i = 0; i < skill_trees.length; i++) {
            String tree = skill_trees[i];
            StringBuilder sb = new StringBuilder();
            for (char c : tree.toCharArray()) {
                if (filterSet.contains(c)) {
                    sb.append(c);
                }
            }
            skillsOnly[i] = sb.toString();
        }

        //---- 여기까지 skills 에 속해있는 문자열이 없는 경우 제거하는 메서드

        for (int x = 0; x < skillsOnly.length; x++) {

            // skills 를 subString 해서 같은지 하나씩 확인
            for (int p = 0; p < skill.length() + 1; p++) {
                String sub_skills = skill.substring(0, p);

                if (sub_skills.equals(skillsOnly[x])) {
                    answer += 1;
                    break;
                }
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        Skill_Tree st = new Skill_Tree();
        String skill = "CBD";
        String[] skillTree = {"BACDE", "CBADF", "AECB", "BDA"};
        st.solution(skill, skillTree);
    }
}
