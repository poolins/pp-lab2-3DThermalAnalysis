# Лабораторная работа №2 по дисциплине "Программирование на языке JAVA"

## Постановка задачи
Решение задачи теплопроводности 3D. 

Метод решения задачи теплопроводности в трех измерениях представляет собой математический подход, направленный на моделирование и анализ трехмерного распределения тепла в сложных структурах. Основан на уравнениях теплопроводности и направлен на понимание пространственно-временного эволюционирования температурных полей внутри твердых тел.

Уравнения теплопроводности в трех измерениях выражаются в частных производных и описывают зависимость температуры от времени и пространственных координат. Для решения таких уравнений применяются численные методы, включающие в себя конечные разностные или конечные элементные подходы. Эти методы позволяют аппроксимировать сложные трехмерные геометрические структуры и численно находить распределение температуры внутри них.

## Особенности реализации
В данной реализации используется явная конечно-разностная схема для решения уравнения теплопроводности в трехмерной области. Эта схема основана на численном методе итераций и позволяет моделировать распределение температуры в пространстве во времени.

Алгоритм решения можно описать следующим образом:
1. Уравнение теплопроводности в явной конечно-разностной схеме имеет вид $\frac{\partial T}{\partial t} = k * \triangledown ^2 T$, где T - температура, k - коэффициент теплопроводности.
2. Явная схема использует значения температуры на предыдущем временном слое для вычисления новых значений на текущем слое.
3. Пространственные производные аппроксимируются разностными квадратичными разностями (центральные разности) по координатам x, y и z.
4. В результате получается итерационный процесс, в котором значения температуры обновляются на каждом временном шаге.

Для параллельных вычислений итерации по пространству (по координате x) распределяются между потоками. Это позволяет ускорить процесс вычислений, так как каждый поток работает с отдельным участком пространства.
После каждой итерации временные слои $T_1$ и $T_2$ обмениваются местами, чтобы обновленные значения использовались на следующем шаге.

Параллельная обработка вычислений ведется посредством интерфейса ***ExecutorService***.

### Параметры распараллеливания
В данной реализации параметр threads определяет количество потоков, которые будут использоваться для выполнения параллельных вычислений. Он передается в конструктор класса Solver, который инициализирует ExecutorService с фиксированным числом потоков. 

Параметр nodes указывает размер трехмерной сетки вдоль каждой из трех координатных осей (X, Y, Z). Размер сетки определяет количество узлов или точек в пространстве, где вычисляются значения температуры.

## Исследование использования многопоточности
Исследование использования многопоточности проводилось при фиксированном значении параметра nodes = 4.
| Число потоков, шт | Время работы алгоритма при количестве узлов в модели 4 шт, мс |
| --- | --- |
| 4 | 691 |
| 10 | 476 |
| 16 | 402 |
| 20 | 430 |
| 64 | 492 |
| 256 | 1248 |
| 1024 | 4233 |

Согласно результам эксперимента, увеличение количества потоков не привело к линейному уменьшению времени выполнения алгоритма. Оптимальное количество потоков для выполнения данного алгоритма 16-20 потоков.

Значительно увеличение времени выполнения алгоритма при количестве потоков 256 и 1024 может быть связано с тем, что каждый поток имеет свои собственные накладные расходы, такие как создание и управление потоками, а также расходы на синхронизацию и координацию между потоками. С увеличением числа потоков накладные расходы могут стать преобладающими.

