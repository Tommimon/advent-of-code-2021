class nodeInHeap:
    def __init__(self, obj: object, cost: int, heur: int):
        self.obj = obj
        self.cost = cost
        self.heur = heur

    def __hash__(self) -> int:
        return hash((str(self.obj), self.cost, self.heur))

    def __repr__(self) -> str:
        return str((str(self.obj), self.cost, self.heur))
    
    def __str__(self) -> str:
        return str((str(self.obj), self.cost, self.heur))

    def evaluation(self):
        return self.cost + self.heur

def minHeapify(nodes: list, pos: int = 0):
    # If it has no children return
    if (pos + 1)*2 > len(nodes):
        return
    
    if nodes[pos].evaluation() > nodes[pos * 2 + 1].evaluation():
        nodes[pos], nodes[pos * 2 + 1] = nodes[pos * 2 + 1], nodes[pos]
        minHeapify(nodes, pos * 2 + 1)
    
    # If it has no right child return
    if (pos +1)*2 + 1 > len(nodes):
        return

    if nodes[pos].evaluation() > nodes[pos * 2 + 2].evaluation():
        nodes[pos], nodes[pos * 2 + 2] = nodes[pos * 2 + 2], nodes[pos]
        minHeapify(nodes, pos * 2 + 2)

def minHeap(nodes: list):
  
        for pos in range(len(nodes)//2, -1, -1):
            minHeapify(nodes, pos)

def insertInHeap(nodes: list, node: nodeInHeap):
    nodes.append(node)
    current = len(nodes) -1
    while current > 0 and nodes[current].evaluation() < nodes[(current - 1)//2].evaluation():
        nodes[current], nodes[(current - 1)//2] = nodes[(current - 1)//2], nodes[current]
        current = (current - 1)//2

def pop(nodes: list):
    res = nodes[0]
    nodes[0] = nodes[-1]
    del nodes[-1]
    minHeapify(nodes)
    return res