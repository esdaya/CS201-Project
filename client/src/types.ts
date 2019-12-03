export interface CP {
  cp_id: number;
  name: string;
}

export interface Student {
  student_id: number;
}

export interface Class {
  class_id: number;
  name: string;
  loc_id: number;
  cps: CP[];
  students: Student[];
}

export const demoClasses: Class[] = [
  {
    class_id: 0,
    name: "CSCI 201",
    loc_id: 0,
    cps: [
      { cp_id: 0, name: "Jack Boyuan Xu" },
      { cp_id: 1, name: "Ying Jin" }
    ],
    students: [{ student_id: 2 }, { student_id: 3 }, { student_id: 13 }]
  },
  {
    class_id: 5,
    name: "EE 109",
    loc_id: 13,
    cps: [{ cp_id: 13, name: "Brian Price" }],
    students: [{ student_id: 1 }]
  },
  {
    class_id: 2,
    name: "CSCI 270",
    loc_id: 3,
    cps: [{ cp_id: 4, name: "Jillian Khoo" }],
    students: [
      { student_id: 5 },
      { student_id: 6 },
      { student_id: 7 },
      { student_id: 8 },
      { student_id: 19 }
    ]
  }
];
