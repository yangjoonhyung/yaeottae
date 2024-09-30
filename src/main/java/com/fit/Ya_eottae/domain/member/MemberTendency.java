package com.fit.Ya_eottae.domain.member;

public enum MemberTendency {

    /**
     * 부드러운 두부김치
     * 인내심 깊은 매운 떡볶이
     * 불닭 열정가
     * 신중한 청양고추
     * 순한 갈비찜
     * 차가운 마라탕
     * 매운 가지
     * 정열의 불고기
     */
    SOFT_TOFU_KIMCHI("부드럽고 매운 것을 조심하는 성격. 조용히 자신의 페이스를 지키면서도 때로는 철저함을 보이기도 함.", "부드러운 두부김치"),
    PATIENCEFUL_SPICY_TTEOKBOKKI("매운 것을 못 먹지만, 활발하고 기다림을 즐김. 매콤함에 도전하려고 하지만 철저하지 않은 면이 있음.", "인내심 싶은 매운 떡볶이"),
    PASSIONATE_BULDAK("맵부심이 가득한 열정가. 활발한 성격으로 매운 것을 즐기며, 철저하거나 느슨할 때도 있음.", "불닭 열정가"),
    CAREFUL_CHEONGYANG_PEPPER("신중하고 차분하지만 매운 것에 대한 자부심이 있음. 철저하게 계획하며, 때로는 느긋함을 보임.", "신중한 청양고추"),
    MILD_BRAISED_RIBS("차분하고 느긋한 성향. 매운 것을 피하지만 서비스가 활발할 때 잘 적응하며, 느긋함과 철저함 사이를 오감.", "순한 갈비찜"),
    COLD_MALATANG("차분하지만 매운 것에 자부심을 가지고 있음. 활발한 서비스와의 조화 속에서 철저함과 느슨함을 오감.", "차가운 마라탕"),
    SPICY_EGGPLANT("활발하지만 조용한 환경에서도 스스로를 도전하는 성향. 매운 것을 피하면서도 철저함을 추구하거나 때로는 포기함.", "매운 가지"),
    BULGOGI_OF_PASSION("조용하지만 내면에 열정과 매운 것에 대한 자부심이 있음. 철저하게 계획하고 실천하는 타입.", "정열의 불고기"),
    NOTEST("성향 테스트를 보지 않음", "깨끗한 물");

    private String explain;
    private String name;

    MemberTendency(String explain, String name) {
        this.explain = explain;
        this.name = name;
    }

    public String getExplain() {
        return explain;
    }

    public String getName() {
        return name;
    }
}
