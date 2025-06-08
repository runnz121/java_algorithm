package company.riid;

/**
 * 정확성 시간 제한 / 메모리 제한
 * 10초 / 2GB
 *
 * 문제 설명
 * 다양한 기업과 많은 지원자들이 참여한 대규모 공개 채용 이벤트가 진행되었습니다. 마침내 엄청난 경쟁률을 뚫고 올라온 N명의 지원자들이 M개의 기업에 입사할 수 있는 기회를 얻었습니다.
 * 이벤트 진행자는 설문을 통해 각 기업으로부터 지원자 선호 순위와 채용 인원수를 수집했고, 각 지원자로부터 기업 선호 순위와 입사를 희망하는 기업 수를 수집했습니다. 이 정보를 바탕으로 아래의 매칭 규칙에 따라 기업과 지원자들을 매칭 하려고 합니다.
 *
 * 아래 표 2개는 각각 기업과 지원자 설문 조사 내용을 나타냅니다. 기업의 이름은 알파벳 대문자, 지원자의 이름은 알파벳 소문자로 나타냅니다.
 *
 * 첫 번째 표는 각 기업이 선호하는 지원자의 순위와 채용하고자 하는 인원수를 나타냅니다. →의 오른쪽에 있을수록 선호도가 낮음을 의미합니다.
 *
 * 기업	지원자에 대한 선호도	채용 인원 수
 * A	f > a > b > d > e > c	2
 * B	c > b > d > f > a > e	2
 * C	e > c > f > a > b > d	2
 *
 * 두 번째 표는 각 지원자가 선호하는 기업의 순위와 입사하고자 하는 기업의 수를 나타냅니다. 어떤 지원자의 입사 희망 기업 수가 1일 경우, 선호도 상위 1위 기업에만 입사할 의지가 있음을 나타냅니다.
 *
 * 지원자	기업에 대한 선호도	입사 희망 기업 수
 * a	B > A > C	1
 * b	B > A > C	3
 * c	B > C > A	2
 * d	A > B > C	3
 * e	C > B > A	3
 * f	A > B > C	2
 *
 * 다음 과정을 따라 기업과 지원자를 매칭 합니다.
 *
 * 1단계. 지원자는 거절당하지 않았다면 다음 라운드에 자신의 선호도가 높은 기업 한 곳에 지원합니다. 입사 희망 기업으로부터 모두 거절당하면 지원을 중단합니다.
 * 2단계. 기업은 채용 인원을 넘지 않는 한도 내에서 선호도 순서대로 지원자를 잠정 선택합니다. 선택하지 않은 지원자들은 거절합니다.
 * 3단계. 거절당한 지원자들은 다시 다른 기업에 지원하고 1단계부터 반복하고, 없다면 잠정 선택을 최종 매칭으로 결정합니다.
 *
 * 아래 그림은 기업과 지원자를 매칭 하는 과정입니다. 총 4라운드를 거치며, 밑줄은 기업이 잠정 선택한 지원자를 나타냅니다. 화살표는 거절당한 지원자가 다음 라운드에 지원하는 기업을 가리킵니다.
 *
 * Round 1
 * A: d f
 * B: b a c e
 * C:
 * Rejected: a, b
 *
 * Round 2
 * A: d f
 * B: c e
 * C:
 * Rejected: d
 *
 * Round 3
 * A: f b
 * B: c e d
 * C:
 * Rejected: d
 *
 * Round 4
 * A: f b
 * B: c e
 * C: d
 * Rejected: -
 *
 * 1라운드에서는 모든 지원자가 각자 가장 선호하는 기업에 지원합니다. A기업은 채용인원이 2명이기 때문에 지원한 2명을 잠정 선택합니다. B기업은 채용인원이 2명이지만 4명이 지원했기 때문에 B기업이 선호하는 c, e를 잠정 선택하고 a, b는 거절합니다. 거절당한 a는 입사 희망 기업이 오직 B이기 때문에 더 이상 다른 기업에 지원하지 않습니다.
 *
 * 2라운드에서는 B에 거절당했던 b가 B 다음으로 선호하는 A기업에 지원합니다. A기업은 3명의 지원자가 선호도가 높으며, f, b를 잠정 선택하고 d는 거절합니다.
 * 3라운드에서는 d가 B기업에 지원합니다. B기업은 3명 중 선호도 높은 c, e를 잠정 선택하고 d는 거절합니다.
 * 4라운드에서는 d가 C기업에 지원합니다. C기업은 지원자의 수가 채용인원 수 이하이기 때문에 d를 잠정 선택합니다. 더 이상 다른 기업에 지원할 지원자가 없기 때문에 매칭을 마무리합니다.
 *
 * 매칭 결과, A기업에는 b, f 지원자가, B기업에는 c, e 지원자가, C기업에는 d 지원자가 매칭 되었습니다.
 *
 * 기업 이름과 지원자 순위, 채용 인원을 나타내는 문자열 배열 companies와 기업 순위, 입사 희망 기업 수를 나타내는 문자열 배열 applicants가 매개변수로 주어집니다. 매칭 결과 각 기업마다 “기업 이름_오름차순으로 정렬된 지원자 이름” 형식의 문자열로 나타내어 배열에 담은 후, 기업 이름으로 오름차순 정렬하여 return 하도록 solution 함수를 완성해주세요.
 *
 * 제한사항
 *
 * companies의 길이(= 전체 기업의 수)는 1개 이상 26 이하입니다.
 *
 * companies 배열의 원소는 “기업이름 지원자순위 채용인원” 형식의 문자열입니다.
 *
 * 기업이름은 알파벳 대문자 A~Z 중 하나입니다.
 *
 * 지원자순위는 모든 지원자 이름이 한 번씩만 등장하도록 내림차순 정렬되어 있습니다. 즉, 기업이 가장 선호하는 지원자가 가장 앞에 나타납니다.
 *
 * 채용인원은 기업이 채용하고자 하는 인원으로 1 이상 전체 지원자 수 이하인 자연수입니다.
 *
 * 모든 기업들의 채용인원 합은 전체 지원자 수 이하입니다.
 *
 * applicants의 길이(= 전체 지원자 수)는 1개 이상 전체 기업의 수 이하인 자연수입니다.
 *
 * applicants 배열의 원소는 “지원자이름 기업순위 입사희망기업수” 형식의 문자열입니다.
 *
 * 지원자 이름은 알파벳 소문자 a~z 중 하나입니다.
 *
 * 기업순위는 모든 기업이름이 한 번씩만 나타나며, 내림차순 정렬되어 있습니다. 즉, 지원자가 가장 선호하는 기업이 가장 앞에 나타납니다.
 *
 * 기업순위는 지원자가 입사를 희망하는 선호도 상위 기업 수 개까지만 나타납니다. 1 이상 전체 기업의 수 이하인 자연수입니다.
 *
 * 입출력 예
 *
 * companies = ["A fabdec 2", "B cebdfa 2", "C ecfdab 2"]
 * applicants = ["a BAC 1", "b BAC 3", "c BCA 2", "d ABC 3", "e BCA 3", "f ABC 2"]
 * result = ["A_bf", "B_ce", "C_d"]
 *
 * companies = ["A abc 2", "B abc 1"]
 * applicants = ["a AB 1", "b AB 1", "c AB 1"]
 * result = ["A_ab", "B_"]
 *
 * 입출력 예 설명
 *
 * 입출력 예 #1
 * 문제 예시와 같습니다.
 *
 * 입출력 예 #2
 * 모든 지원자들이 A기업에만 입사하기를 희망합니다. A기업으로부터 거절당한 c지원자는 더 이상 다른 기업에 지원하지 않으며, B기업에는 매칭 된 지원자가 없습니다.
 *
 *
 * 비슷한 문제
 * https://www.acmicpc.net/problem/12022
 * https://www.acmicpc.net/problem/9002
 * https://www.acmicpc.net/problem/3761
 * https://www.acmicpc.net/problem/20009
 * https://www.acmicpc.net/problem/27985
 */

import java.util.*;
public class Sol2 {

    public String[] solution(String[] companies, String[] applicants) {
        // 1) 회사 정보 파싱
        Map<String, Company> companyMap = new HashMap<>();
        for (String comp : companies) {
            String[] p = comp.split(" ");
            String name = p[0];
            String prefOrder = p[1];           // ex) "fabdec"
            int capacity = Integer.parseInt(p[2]);
            companyMap.put(name, new Company(name, capacity, prefOrder));
        }

        // 2) 지원자 정보 파싱
        Map<String, Applicant> applicantMap = new HashMap<>();
        Queue<String> queue = new LinkedList<>();
        for (String app : applicants) {
            String[] p = app.split(" ");
            String name = p[0];
            String prefOrder = p[1];          // ex) "BAC"
            int maxApps = Integer.parseInt(p[2]);
            // 선호 기업은 상위 maxApps개
            List<String> prefs = new ArrayList<>();
            for (int i = 0; i < maxApps; i++) {
                prefs.add(prefOrder.substring(i, i+1));
            }
            applicantMap.put(name, new Applicant(name, prefs));
            queue.offer(name);
        }

        // 3) 매칭 라운드 반복
        while (!queue.isEmpty()) {
            String appName = queue.poll();
            Applicant applicant = applicantMap.get(appName);
            // 더 지원할 기업이 없으면 중단
            if (!applicant.hasNext()) continue;

            String compName = applicant.next();
            Company company = companyMap.get(compName);
            // 지원하고, 탈락자가 있으면 queue에 다시 등록
            String rejected = company.match(appName);
            if (rejected != null) {
                // 탈락자도 다음 선호기업에 지원 가능하면 다시 queue에
                if (applicantMap.get(rejected).hasNext()) {
                    queue.offer(rejected);
                }
            }
        }

        // 4) 결과 정리: 회사 이름 오름차순, 지원자 이름 알파벳 오름차순
        List<String> names = new ArrayList<>(companyMap.keySet());
        Collections.sort(names);
        String[] answer = new String[names.size()];
        for (int i = 0; i < names.size(); i++) {
            Company c = companyMap.get(names.get(i));
            List<String> emps = new ArrayList<>(c.accepted);
            Collections.sort(emps);
            String joined = String.join("", emps);
            answer[i] = names.get(i) + "_" + joined;
        }
        return answer;
    }

    // 회사 모델: capacity, 선호도 순위 맵, 현재 잠정수락된 지원자들
    static class Company {
        String name;
        int capacity;
        // 지원자 이름 → 우선순위 숫자(작을수록 상위 선호)
        Map<String, Integer> rank = new HashMap<>();
        // 현재 잠정 수락된 지원자들
        List<String> accepted = new ArrayList<>();

        Company(String name, int capacity, String prefOrder) {
            this.name = name;
            this.capacity = capacity;
            for (int i = 0; i < prefOrder.length(); i++) {
                rank.put(prefOrder.substring(i, i+1), i);
            }
        }

        /**
         * @param applicant 지원자 이름
         * @return 탈락된 지원자 이름 (없으면 null)
         */
        String match(String applicant) {
            accepted.add(applicant);
            if (accepted.size() <= capacity) {
                return null; // 아직 자리 남음
            }
            // 자리 초과: 가장 선호도가 낮은(순위 숫자가 큰) 지원자 탈락
            String worst = null;
            int worstRank = -1;
            for (String a : accepted) {
                int r = rank.get(a);
                if (r > worstRank) {
                    worstRank = r;
                    worst = a;
                }
            }
            accepted.remove(worst);
            return worst;
        }
    }

    // 지원자 모델: 선호 기업 리스트와 다음 지원 인덱스
    static class Applicant {
        String name;
        List<String> prefs;
        int idx = 0;

        Applicant(String name, List<String> prefs) {
            this.name = name;
            this.prefs = prefs;
        }

        boolean hasNext() {
            return idx < prefs.size();
        }

        String next() {
            return prefs.get(idx++);
        }
    }
}
